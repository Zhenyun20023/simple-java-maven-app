
pid=$(jps | grep App | awk '{print $1}')
mkdir ${pid}
cd ${pid}
jcmd $pid VM.native_memory > native_memory.txt
pmap -X $pid > pmap.txt
cat /proc/${pid}/maps > maps.txt
perl /home/ec2-user/testing/javaJniMemUsage/javaJniMemUsage.pl maps.txt  > maps.perl.txt
jmap -dump:live,format=b,file=/tmp/jmap.hprof ${pid}
jcmd ${pid} GC.heap_dump /tmp/jcmd.hprof
