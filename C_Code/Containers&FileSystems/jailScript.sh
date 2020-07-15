
#!/usr/bin/env bash


sudo mkdir /new-root
sudo mkdir /new-root/{bin,etc}
sudo mkdir -p /new-root/{lib/x86_64-linux-gnu,lib64}
sudo cp /bin/bash /new-root/bin/
sudo cp /bin/ls /new-root/bin/
 
for i in /lib/x86_64-linux-gnu/libtinfo.so.5 /lib/x86_64-linux-gnu/libdl.so.2 /lib/x86_64-linux-gnu/libc.so.6 ; do sudo cp -v $i /new-root/lib/x86_64-linux-gnu/; done

sudo cp /lib64/ld-linux-x86-64.so.2 /new-root/lib64/\

for i in /lib/x86_64-linux-gnu/libselinux.so.1 /lib/x86_64-linux-gnu/libc.so.6 /lib/x86_64-linux-gnu/libpcre.so.3 /lib/x86_64-linux-gnu/libdl.so.2 /lib/x86_64-linux-gnu/libpthread.so.0 ; do sudo cp -v $i /new-root/lib/x86_64-linux-gnu/; done\

for i in /lib/x86_64-linux-gnu/libc.so.6 /lib/x86_64-linux-gnu/libtinfo.so.5 ; do sudo cp -v $i /new-root/lib/x86_64-linux-gnu/; done

echo "PS1='JAIL $ ' " | sudo tee /new-root/etc/bash.bashrc

sudo chroot /new-root/ /bin/bash

echo "You Are Now in Jail, Enjoy!"
