package com.github.yedp.ez.util;

import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class QyWxUtil {
    private static final String UPLOAD_URL = "https://qyapi.weixin.qq.com/cgi-bin/webhook/upload_media?type=file&key=";
    private static final String SEND_URL = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=";


    /**
     * 上传文件到企业微信
     *
     * @param groupId 群key
     * @param file    文件
     * @return 文件id
     * @throws IOException io异常
     */
    public static String uploadFile(String groupId, File file) throws IOException {
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        multipartEntityBuilder.addBinaryBody("file", file);
        String res = HttpUtil.ReturnPostBody(UPLOAD_URL.concat(groupId), multipartEntityBuilder.build());
        if (StringUtils.isEmpty(res)) {
            return StringUtils.EMPTY;
        }
        HashMap map = JsonUtil.readValue(res, HashMap.class);
        if (map != null) {
            Object o = map.get("media_id");
            if (o != null) {
                return o.toString();
            }
        }
        return StringUtils.EMPTY;
    }

    /**
     * 发送文件到群
     *
     * @param groupId 群key
     * @param fileId  文件id
     * @return 结果
     * @throws IOException 异常
     */
    public static String sendFile(String groupId, String fileId) throws IOException {
        return HttpUtil.ReturnPostBody(SEND_URL.concat(groupId), "{\"msgtype\":\"file\",\"file\":{\"media_id\":\"".concat(fileId).concat("\"}}"));
    }


    /**
     * 发送消息
     *
     * @param groupId 群key
     * @param msg     消息
     * @return 结果
     * @throws IOException 异常
     */
    public static String sendMsg(String groupId, String msg) throws IOException {
        return HttpUtil.ReturnPostBody(SEND_URL.concat(groupId), "{\"msgtype\":\"text\",\"text\":{\"content\":\"".concat(msg).concat("\"}}"));
    }

    /**
     * 发送消息
     *
     * @param groupId       群key
     * @param msg           消息
     * @param mentionedPhoneList 需关注人的手机号码
     * @return 结果
     * @throws IOException 异常
     */
    public static String sendMsg(String groupId, String msg, List<String> mentionedPhoneList) throws IOException {
        return HttpUtil.ReturnPostBody(SEND_URL.concat(groupId), "{\"msgtype\":\"text\",\"text\":{\"content\":\"".concat(msg).concat("\", \"mentioned_mobile_list\":" + JsonUtil.toString(mentionedPhoneList) + "}}"));
    }

    /**
     * 发送消息markdown格式
     *
     * @param groupId 群key
     * @param msg     消息
     * @return 结果
     * @throws IOException 异常
     */
    public static String sendMsgMarkdown(String groupId, String msg) throws IOException {
        return HttpUtil.ReturnPostBody(SEND_URL.concat(groupId), "{\"msgtype\":\"markdown\",\"markdown\":{\"content\":\"".concat(msg).concat("\"}}"));
    }
}
