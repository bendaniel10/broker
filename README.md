# broker
A server that can act as a middle man between a client and a target url. This way, a user can specify what urls to provide mock responses for. 

## Prerequisites
1. Point your app's URL to the mocking server's URL. For example, if my base URL is https://foobar.com then I'll change that to http://ip_address_where_server_is_running:8080. The ip address has mostly been my computer's ip address since that's where I'm running the server.
2. Add my broker_project_token header value for the project which I intend to mock. On android, it should be similar to this if you're using OkHttpClient :
```kotlin
// An interceptor that adds the header to all my outgoing requests.
client.newBuilder()
.addInterceptor {
  it.request().newBuilder()
      .addHeader("broker_project_token", "12345_a_valid_broker_project_token_67890")
      .build().run {
        it.proceed(this)
      }
}
```
3. For network security on android devices, from Android 9 (API level 28) and above, cleartext support is disabled by default. I had to do this step since I'm running the server on my computer. This [stackoverflow answer](https://stackoverflow.com/questions/45940861/android-8-cleartext-http-traffic-not-permitted/50834600#50834600) shows how to do this on android. 

---
## Run
```shell script
cd broker-app
.././gradlew run
```

