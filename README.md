## APIs

* ### StringUtils
```
isEmpty ：是否为空
isAllEmpty ：是否都未空
hasEmpty ：是否包含有空
hasEqual ：是否包含有相同的值，不考虑空值
getMoney ：获取金额文字
addComma ：获取千分位格式金额文字
getMessageTimeStr ：显示消息时间字符串，区分今日和之前的
getHelloStr ：问好文字
getStringOrEmpty ：获取缺省处理的字符串
getStringOrEmpty ：获取缺省处理的字符串
getDistance ：1km以上 显示 1.5km
getHighLightStr ：获取高亮文字
getHighLightStr ：获取高亮文字
list2str ：数组转为字符串，用逗号,隔开。数组为空返回 ""
str2list ：逗号,分割的字符串转为list，忽略空字符串部分。
getPercent ：获取百分比。最多保留两位小数，并且不四舍五入 0.5468 -> 54.6%
HumpToUnderlineLower ：驼峰命名转为下划线命名(小写) ShinhoHome -> shinho_home
isPhone ：是否为手机号，简单判断：1开始11位。
```

* ### FileUtils
```
getAssetsFileString ：读取asset文件内容
getAppDir ：获取App根目录
getUploadDir ：获取App上传目录
createUploadPhotoName ：产生一个唯一的上传图片名
createPhotoName ：产生一个唯一的图片名
createUploadMergePhotoName ：产生一个唯一的上传拼接的图片名
getDownloadDir ：获取App下载目录
hasSdcard ：是否有SD卡
delete ：删除某个文件夹下满足过滤条件的文件
getFileNameWithoutExtension ：获取某个文件不含后缀的名字
writeToDisk ：将InputStream写入文件
installApk ：安装某个apk文件到手机
hasFileInAsset ：判断asset里是否有某个文件
fileToBase64 ：将文件转化为Base64
Base64ToFile ：将Base64转化为文件
saveBitmapIntoJPG ：按照指定名字将Bitmap保存为图片
stitchBitmap ：将多张图横向拼接为一张Bitmap
stitchBitmapVertical ：将多张图竖向拼接为一张Bitmap
getImageDir ：获取存放图片的文件夹
```

* ### MainActivity
```
```

* ### NetUtils
```
isConnected ：判断网络是否连接
getNetworkState ：获取当前网络连接类型
isWifi ：判断是否是wifi连接
openSetting ：打开网络设置界面
```

* ### DisplayUtils
```
px2dp ：将px值转换为dp值
dp2px ：将dp值转换为px值
px2sp ：将px值转换为sp值
sp2px ：将sp值转换为px值
getScreenWidth ：获取屏幕宽度
getScreenHeight ：获取屏幕高度
isLongScreen ：判断是否是全面屏（长屏），尺寸大于 18:9的就算作长屏幕
```

* ### DateUtils
```
calculateDayDiff ：计算天数差
calculateDayDiffDetail ：计算天数差
isToday ：判断Calendar是否是今天
isToday ：判断时间戳是否是今天
isSameDay ：判断两个时间戳是否为同一天
isSameDay ：判断两个Calendar是否为同一天
isSameMonth ：判断两个Calendar是否为同一个月
str2date ：将一个时间String解析为一个Data
str2date ：按照 yyyy.M.d HH:mm 将String解析为Date
date2str ：按照 yyyy.M.d HH:mm 将Date输出为String
date2str ：按照format格式返回Date
str2calendar ：按照 yyyy.M.d HH:mm 将String解析为Calendar
str2calendar ：按照指定format，将String解析为Calendar
calendar2str ：按照 yyyy.M.d HH:mm 将Calendar输出为String
timeInMillsToDayStr ：按照 yyyy.M.d 将String时间戳输出为String
timeInMillsToDayStr ：按照 yyyy.M.d 将Long时间戳输出为String
timeInMillsToDayMinuteStr ：按照 yyyy.M.d HH:mm 将String时间戳输出为String
timeInMillsToStr ：按照指定pattern将String时间戳输出为String
timeInMillsToDayMinuteStr ：按照 yyyy.M.d HH:mm 将Long时间戳输出为String
calendar2str ：按照指定format将Long时间戳输出为String
periodCalendar2Str ：按照 yyyy.M.d HH:mm - yyyy.M.d HH:mm 将两个Long时间戳输出为String
ServerDateString2App ：将字符串由yyyy-MM-dd格式转化为yyyy.M.d格式
AppDateString2Server ：将字符串由yyyy.M.d格式转化为yyyy-MM-dd格式
dateStr2Str ：将字符串由fromPattern格式转化为toPattern
serverDateString2Date ：将String按照yyyy-MM-dd格式解析为Date
serverDateString2Calendar ：将String按照yyyy-MM-dd格式解析为Calendar
getDayCalendar ：获取今天0点0分0秒的Calendar
clearToDayCalendar ：将Calendar的时分秒毫秒置为零
getMonthCalendar ：null
getCalendar ：将long时间戳转化为Calendar
getCalendar ：将String时间戳转化为Calendar
clearToMonthCalendar ：将Calendar信息重置到某月1日
removeSecond ：将时间String按照指定pattern解析后以yyyy.M.d HH:mm输出
removeSecondAio ：将时间String按照yyyy-MM-dd HH:mm:ss解析后以yyyy.M.d HH:mm输出
```

* ### TintBarUtils
```
setWindowStatusBar ：设置状态栏风格
flymeSetStatusBarLightMode ：设置状态栏图标为深色和魅族特定的文字风格  可以用来判断是否为Flyme用户
MIUISetStatusBarLightMode ：设置状态栏字体图标为深色，需要MIUIV6以上
```

* ### DeviceUtils
```
saveImage ：保存Bitmap到指定绝对路径和图库
getSimplePackageName ：获取简单包名
openBrowser ：调用第三方浏览器打开
getAppVersionName ：获取版本名称
getAppVersionCode ：获取版本号 100
getIMEI ：获取IMEI号
promptInstall ：安装文件
```

* ### KeyboardUtils
```
showSoftInput ：展示软键盘
hideSoftInput ：隐藏软键盘
toggleSoftInput ：触发软键盘打开或关闭
```
