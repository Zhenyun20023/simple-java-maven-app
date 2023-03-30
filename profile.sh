
pid=$(jps | grep App | awk '{print $1}')
mkdir ${pid}
cd ${pid}
jcmd $pid VM.native_memory > native_memory.txt
pmap -X $pid > pmap.txt
cat /proc/${pid}/maps > maps.txt
perl /home/ec2-user/testing/javaJniMemUsage/javaJniMemUsage.pl maps.txt  > perl.maps.txt
jmap -dump:live,format=b,file=jmap.hprof ${pid}
