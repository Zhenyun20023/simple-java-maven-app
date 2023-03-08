
pid=$(jps | grep App | awk '{print $1}')
jcmd $pid VM.native_memory > native_memory.txt
pmap -X $pid > pmap.txt
cat /proc/${pid}/maps > maps.txt