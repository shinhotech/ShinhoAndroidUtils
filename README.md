## APIs

* ### StringUtils
```
isEmpty				        是否为空
isAllEmpty			      是否都未空
hasEmpty				      是否包含有空
hasEqual				      是否包含有相同的值，不考虑空值
getMoney				      获取金额文字
addComma				      获取千分位格式金额文字
getMessageTimeStr		  显示消息时间字符串，区分今日和之前的
getHelloStr				    18:01-6:00 晚上好
getStringOrEmpty		  获取缺省处理的字符串
getStringOrEmpty		  获取缺省处理的字符串
getDistance				    1km以上 显示 1.5km
getHighLightStr			  获取高亮文字
getHighLightStr			  获取高亮文字
list2str				      数组转为字符串，用逗号,隔开。数组为空返回 ""
str2list				      逗号,分割的字符串转为list，忽略空字符串部分。
getPercent			  	  获取百分比。最多保留两位小数，并且不四舍五入 0.5468 -> 54.6%
HumpToUnderlineLower  驼峰命名转为下划线命名(小写) ShinhoHome -> shinho_home
isPhone				        是否为手机号，简单判断：1开始11位。
```

* ### FileUtils
```
getAssetsFileString				  读取asset文件内容
getAppDir				            获取App根目录
getUploadDir				        获取App上传目录
createUploadPhotoName				产生一个唯一的上传图片名
createPhotoName				      产生一个唯一的图片名
createUploadMergePhotoName	产生一个唯一的上传拼接的图片名
getDownloadDir			      	获取App下载目录
hasSdcard				            是否有SD卡
delete				              删除某个文件夹下满足过滤条件的文件
getFileNameWithoutExtension	获取某个文件不含后缀的名字
writeToDisk				          将InputStream写入文件
installApk				          安装某个apk文件到手机
hasFileInAsset				      判断asset里是否有某个文件
fileToBase64				        将文件转化为Base64
Base64ToFile				        将Base64转化为文件
saveBitmapIntoJPG				    按照指定名字将Bitmap保存为图片
stitchBitmap				        将多张图横向拼接为一张Bitmap
stitchBitmapVertical				将多张图竖向拼接为一张Bitmap
getImageDir				          获取存放图片的文件夹
```
