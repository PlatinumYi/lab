package com.xuenan.lab.user_management.service.impl;

import com.xuenan.lab.entity.LoginSession;
import com.xuenan.lab.entity.User;
import com.xuenan.lab.tool.MD5Tools;
import com.xuenan.lab.tool.RandomSessionKey;
import com.xuenan.lab.user_management.dao.LoginSessionDao;
import com.xuenan.lab.user_management.dao.UserDao;
import com.xuenan.lab.user_management.model.ResponseModel;
import com.xuenan.lab.user_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserDao userDao ;

    @Autowired
    private LoginSessionDao loginSessionDao ;

    //所有session的生命周期为24小时
    public static final int SESSION_LIFE = 24 ;

    @Override
    public ResponseModel register(String schoolNumber, String name, String password) {

        ResponseModel responseModel ;
        // 获取当前北京时间
        Date LondonToday = new Date() ;
        Long currentMillsToday = LondonToday.getTime() + (long)(3600*1000*8) ;
        Date BeijingToday = new Date(currentMillsToday);

        //获取MD5加密后的密码
        password = MD5Tools.MD5(password);

        if (userDao.checkSchoolNumberExist(schoolNumber)>0){
            responseModel = new ResponseModel(2001,"该学号已被注册") ;
        }else {
            Integer result = userDao.register(schoolNumber,password,name,BeijingToday) ;
            if( result == 1 ){
                responseModel = new ResponseModel() ;
            }else {
                responseModel = new ResponseModel(2002,"远程服务器没有响应请求") ;
            }
        }
        return responseModel ;
    }

    @Override
    public ResponseModel login(String schoolNumber, String password) {

        ResponseModel responseModel ;
        // 获取当前北京时间
        Date LondonToday = new Date() ;
        Long currentMillsToday = LondonToday.getTime() + (long)(3600*1000*8) ;
        Date BeijingToday = new Date(currentMillsToday);

        //获取Session的最早有效时间
        Long millsValid = BeijingToday.getTime() - (long)(3600*1000*SESSION_LIFE);

        password = MD5Tools.MD5(password);
        User user = userDao.queryUserBySchoolNumberAndPassword(schoolNumber,password);

        if( user==null  ){
            responseModel = new ResponseModel(2003,"学号与密码不匹配") ;
        }//若学号与密码不匹配则直接返回错误
        else if(user.getValid()==0){
            responseModel = new ResponseModel(2004,"您的用户已被注销，请联系管理员恢复") ;
        }
        else{
            LoginSession session = loginSessionDao.queryLoginSessionBySchoolNumber(user.getId()) ;
            if (session == null ){
                String token = RandomSessionKey.getRandomChar(30);
                loginSessionDao.createLoginSession(token,user.getId(),BeijingToday);
            }//session不存在则直接创建session
            else {
                if (session.getLoginTime().getTime() < millsValid) {
                    String token = RandomSessionKey.getRandomChar(30);
                    loginSessionDao.updateSessionKey(token,user.getId(),BeijingToday);
                }
            }//若session已经存在，则判断是否过时，进行更新

            //再次获取session，判断对session的操作是否生效
            session = loginSessionDao.queryLoginSessionBySchoolNumber(user.getId());
            if( session !=null && session.getLoginTime().getTime() >= millsValid ){
                responseModel = new ResponseModel();
                responseModel.setData(session);
            } // 若判断session合法，则返回登录成功信息
            else {
                responseModel = new ResponseModel(2005,"token没有正确地被存储，请重新登录");
            }
        }

        return responseModel ;

    }

    @Override
    public ResponseModel logout(String token) {

        loginSessionDao.removeSessionKey(token);
        if( loginSessionDao.queryLoginSessionByKey(token) != null ){
            return new ResponseModel(2006,"token删除失败");
        }else {
            return new ResponseModel();
        }
    }

    @Override
    public ResponseModel queryallValid() {

        List<User> users = userDao.queryValidUsers() ;
        ResponseModel model = new ResponseModel() ;
        model.setData(users);
        return model ;
    }

    @Override
    public ResponseModel queryallInvalid() {

        List<User> users = userDao.queryInvalidUsers() ;
        ResponseModel model = new ResponseModel() ;
        model.setData(users);
        return model ;
    }

    @Override
    public ResponseModel banUser(Integer id) {

        ResponseModel model ;
        User user = userDao.queryUserById(id) ;
        if( user == null ){
            model = new ResponseModel(2007,"目标用户不存在");
        }else if( user.getType()  == 3 ){
            model = new ResponseModel(2008,"不能操作管理员");
        }else if( user.getValid() == 0 ){
            model = new ResponseModel(2009,"用户已经被禁用了");
        }else {
            Integer result = userDao.banUser(id) ;
            if(result>0){
                model = new ResponseModel() ;
            }else {
                model = new ResponseModel(2010,"用户禁用失败,请重试");
            }
        }
        return model ;
    }

    @Override
    public ResponseModel enableUser(Integer id) {
        ResponseModel model ;
        User user = userDao.queryUserById(id) ;
        if( user == null ){
            model = new ResponseModel(2007,"目标用户不存在");
        }else if( user.getType()  == 3 ){
            model = new ResponseModel(2008,"不能操作管理员");
        }else if( user.getValid() == 1 ){
            model = new ResponseModel(2011,"用户已经处于可使用状态");
        }else {
            Integer result = userDao.enableUser(id);
            if(result>0){
                model = new ResponseModel() ;
            }else {
                model = new ResponseModel(2012,"用户启用失败,请重试");
            }
        }
        return model ;
    }

    @Override
    public ResponseModel setUserType(Integer id, Integer type) {

        ResponseModel model ;

        User user = userDao.queryUserById(id) ;
        if( user == null ){
            model = new ResponseModel(2007,"目标用户不存在");
        }else if( user.getType()  == 3 ){
            model = new ResponseModel(2008,"不能操作管理员");
        } else if( type<1 || type > 3){
            model = new ResponseModel(2013,"修改类型非法");
        }else {
            Integer result = userDao.setUserType(type,id);
            if( result>0 ){
                model = new ResponseModel() ;
            }else {
                model = new ResponseModel(2014,"修改用户类型失败");
            }
        }
        return model ;
    }

    @Override
    public ResponseModel deleteUser(Integer id) {

        ResponseModel model ;
        User user = userDao.queryUserById(id) ;
        if( user == null ){
            model = new ResponseModel(2007,"目标用户不存在");
        }else if( user.getType()  == 3 ){
            model = new ResponseModel(2008,"不能操作管理员");
        }else {
            Integer result = userDao.removeUserById(id);
            if( result>0 ){
                model = new ResponseModel();
            }else {
                model = new ResponseModel(2015,"删除用户失败");
            }
        }
        return model ;
    }

    @Override
    public ResponseModel queryUserByNameOrSchoolNumber(String key) {
        List<User> users = userDao.queryUserBySchoolNumberOrName(key);
        ResponseModel model = new ResponseModel() ;
        model.setData(users);
        return model ;
    }


}
