cd src/main/java;
javac com/mycompany/app/*.java;
java -cp . -Xms500M -Xmx500M -XX:+UseG1GC -XX:MaxGCPauseMillis=200 -XX:NativeMemoryTracking=summary -XX:+AlwaysPreTouch -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/tmp/logs/ -XX:+ExitOnOutOfMemoryError -XX:MaxDirectMemorySize=400M  com.mycompany.app.App


# -Djdk.nio.maxCachedBufferSize=262144


#java -cp . -Xlog:gc+age*,safepoint:file=/tmp/logs/gc.log:time,level,tags:filecount=5,filesize=100M -Xms500M -Xmx500M -XX:+UseG1GC -XX:MaxGCPauseMillis=200 -XX:NativeMemoryTracking=summary -XX:+AlwaysPreTouch -Dcom.sun.management.jmxremote.port=1099 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/tmp/logs/ -XX:+ExitOnOutOfMemoryError -XX:MaxDirectMemorySize=1200M  com.mycompany.app.MemoryLeak
