### Volume类型
    Kubernetes支持很多种类的volume，包括：emptyDir、hostPath、gcePersistentDisk、awsElasticBlockStore、nfs、iscsi、flocker、
    glusterfs、rbd、cephfs、gitRepo、secret、persistentVolumeClaim、downwardAPI、azureFileVolume、azureDisk、vsphereVolume、
    Quobyte、PortworxVolume、ScaleIO。

### EmptyDir（本地数据卷）
    EmptyDir类型的volume创建于pod被调度到某个宿主机上的时候，而同一个pod内的容器都能读写EmptyDir中的同一个文件。
    一旦这个pod离开了这个宿主机，EmptyDirr中的数据就会被永久删除。所以目前EmptyDir类型的volume主要用作临时空间，
    比如Web服务器写日志或者tmp文件需要的临时目录。
 
 
### HostDir（本地数据卷）
    HostDir属性的volume使得对应的容器能够访问当前宿主机上的指定目录。
    例如，需要运行一个访问Docker系统目录的容器，那么就使用/var/lib/docker目录作为一个HostDir类型的volume；
    或者要在一个容器内部运行CAdvisor，那么就使用/dev/cgroups目录作为一个HostDir类型的volume。
    一旦这个pod离开了这个宿主机，HostDir中的数据虽然不会被永久删除，但数据也不会随pod迁移到其他宿主机上。
    因此，需要注意的是，由于各个宿主机上的文件系统结构和内容并不一定完全相同，所以相同pod的HostDir可能会在不同的宿
    主机上表现出不同的行为。

### NFS（网络数据卷）
    NFS类型的volume。允许一块现有的网络硬盘在同一个pod内的容器间共享。
    在Kubernetes中，可以通过nfs类型的存储卷将现有的NFS（网络文件系统）到的挂接到Pod中。
    在移除Pod时，NFS存储卷中的内容被不会被删除，只是将存储卷卸载而已。
    这意味着在NFS存储卷总可以预先填充数据，并且可以在Pod之间共享数据。NFS可以被同时挂接到多个Pod中，并能同时进行写入。
    需要注意的是：在使用nfs存储卷之前，必须已正确部署和运行NFS服务器，并已经设置了共享目录。
 
### persistentVolumeClaim 
    persistentVolumeClaim类型存储卷将PersistentVolume挂接到Pod中作为存储卷。使用此类型的存储卷，用户并不知道存储卷的详细信息。