FROM eclipse-temurin:20
COPY target/police.jar /opt/police.jar
RUN useradd -ms /bin/sh notSec2075 \
    && echo "root:Docker!" | chpasswd \
    && chown -R notSec2075 /opt/
USER notSec2075
ENTRYPOINT ["java", "--add-opens", "java.base/java.time.zone=ALL-UNNAMED", "--add-opens", "java.base/java.time=ALL-UNNAMED", \
            "--add-opens", "java.base/java.math=ALL-UNNAMED", "--add-opens", "java.base/java.lang=ALL-UNNAMED", \
            "--add-opens", "java.management/sun.management=ALL-UNNAMED", \
            "--add-opens", "java.base/java.time.LocalDateTime=ALL-UNNAMED", \
            "-XX:+UseZGC", "-XX:MinHeapSize=1G", "-XX:InitialHeapSize=1G", "-XX:MaxHeapSize=8G", \
            "-XX:ParallelGCThreads=100", "-XX:ConcGCThreads=30", "--enable-preview", \
            "-XX:SoftRefLRUPolicyMSPerMB=0","-XX:ZCollectionInterval=30", "-XX:ZFragmentationLimit=10", \
            "-XX:MaxGCPauseMillis=5", "-XX:+UseCompressedClassPointers", "-XX:-UseCompressedOops", "-XX:ZUncommitDelay=30", \
            "-XX:InitiatingHeapOccupancyPercent=35", "-XX:G1HeapRegionSize=16M", \
            "-XX:MinMetaspaceFreeRatio=50", "-XX:MaxMetaspaceFreeRatio=100",  \
            "-XX:+HeapDumpOnOutOfMemoryError", "-Dsun.net.inetaddr.ttl=60", "-Dsun.net.client.defaultConnectTimeout=10000", "-Dsun.net.client.defaultReadTimeout=30000", \
            "-XX:+UnlockExperimentalVMOptions", "-XshowSettings:vm" ,"-jar","/opt/police.jar"]
EXPOSE 12347


