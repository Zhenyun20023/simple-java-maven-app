cd src/main/java;
javac com/mycompany/app/*.java;
java -cp . -Xlog:gc+age*,safepoint:file=/tmp/logs/gc.log:time,level,tags:filecount=5,filesize=100M -Xms500M -Xmx500M -XX:+UseG1GC -XX:MaxGCPauseMillis=200 -XX:NativeMemoryTracking=summary -XX:+AlwaysPreTouch -Dcom.sun.management.jmxremote.port=1099 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/tmp/logs/ -agentpath:/home/ec2-user/testing/async-profiler/build/libasyncProfiler.so=start,event=malloc,file=/tmp/logs/profile.html  -XX:+ExitOnOutOfMemoryError -XX:MaxDirectMemorySize=1000M com.mycompany.app.App

