cd src/main/java;
rm com/mycompany/app/*.class
echo " deleted old classes.";
javac com/mycompany/app/*.java;
java com.mycompany.app.App -cp . -Xlog:gc+age*,safepoint:file=/tmp/logs/gc.log:time,level,tags:filecount=5,filesize=100M -Xms500M -Xmx500M -XX:+UseG1GC -XX:MaxGCPauseMillis=200 -XX:NativeMemoryTracking=summary -XX:+AlwaysPreTouch