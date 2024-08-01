#!/bin/bash
# scripts/configure_nginx.sh

# Nginx 설정 파일 복사
cp /home/ubuntu/app/nginx.conf /etc/nginx/nginx.conf

# Nginx 재시작
systemctl restart nginx
