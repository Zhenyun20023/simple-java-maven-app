cd src/main/java;
javac com/mycompany/app/*.java;
java -cp . -Xlog:gc+age*,safepoint:file=/tmp/logs/gc.log:time,level,tags:filecount=5,filesize=100M -Xms500M -Xmx500M -XX:+UseG1GC -XX:MaxGCPauseMillis=200 -XX:NativeMemoryTracking=summary -XX:+AlwaysPreTouch -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/tmp/logs/ -XX:MaxDirectMemorySize=400M com.mycompany.app.App

