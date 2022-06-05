# rates

./gradlew clean build

docker build -t rates:latest .

docker run -p 9090:9090 rates -n rates

