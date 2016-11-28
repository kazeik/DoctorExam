package com.kazeik.doctor.doctorexam.utils;

import com.kazeik.doctor.doctorexam.bean.CityBean;
import com.kazeik.doctor.doctorexam.bean.LoginUserBean;

/**
 * Created by kazeik.chen on 2016/2/18 0018 10:36.
 * email:kazeik@163.com ,QQ:77132995
 */
public class ApiUtils {
    public static final String baseUrl ="http://appapi.kaohewang.com/";

    public static final String VIDEODOWN = "com.kazeik.exam.videoDown";

    public static CityBean cityBean;
    public static LoginUserBean userInfoBean;

    public static final String getAdds = "index/systems";
    /**
     * 用户注册
     */
    public static final String register = "users/register";

    /**
     * 用户登录
     */
    public static final String userLogin = "index/login";

    /**
     *
     */
    public static final String getVerfiyCode = "index/sendsms";

    public static final String getUserInfo = "users/myInfo";
    public static final String payInfo ="users/paymentDetails";

    public static final String activateCard = "users/activateCard";
    public static final String changePass = "users/reSetPassword";

    public static final String getNewsType = "news/type";
    public static  final String newsList = "news/list";
    public static final String newsDetail = "news/detail";

    public static final String orderList = "order/list";

    public static final String notifyList ="notice/list";
    public static final String notifyDetail= "notice/detail";

    public static final String mockList = "mockexam/paperlist";
    public static final String mockBody = "mockexam/exam";
//    public static final String mockDownload = "mockexam/download";
    public static final String submitAnswer = "mockexam/examsub";

    public static final String helpList = "help/list";
    public static final String helpType = "help/type";
    public static final String helpDetail= "help/detail";

    public static final String chatList = "chat/servicelist";

    public static final String chatHistory = "chat/history";


    public static final String drugDetail = "medicine/detail";
    public static final String drugSearch = "medicine/search";

    public static final String allLessonList = "course/list";

    public static final String myLessonList = "course/mycourse";
    public static final String lessonDetail = "course/detail";

    public static final String videoDownload = "video/download";
    public static  final String ebookDownload = "ebook/epubdownload";

    public static final String exchangeSurvey="survey/exchange";
    public static final String surveyList = "survey/list";
    public static final String gotoSurvey = "survey/gotoSurvey";

    public static final String getBanner = "index/banner";

    public static final String checkAnswer = "mockexam/checkAnswer";

    public static final String orderPay = "order/pay";
    public static final String cancelOrder = "order/cancelOrder";
    public static final String orderWebViewPay = "order/alipay";
    public static final String orderMoreInfo = "order/detail";

    public static final String orderInfo = "order/orderinfo";

    public static final String mockAddCar = "mockexam/addPapertoCart";
    public static final String addCar = "cart/add";
    public static final String cartList = "cart/list";
    public static final String cartDelete="cart/delete";

    public static final String setChange = "users/setExchangeAcount";

    /**
     * 下载
     */
    public static final String examDownload = "exam/download";
    /**
     * 提交答案
     */
    public static final String examSubmit = "exam/submit";
    /**
     * 查看答案
     */
    public static final String examCheckAnswer="exam/checkanswer";
}
