By [Dmytro Nasyrov, Founder, CTO at Pharos Production Inc.](https://www.linkedin.com/in/dmytronasyrov/)
And [Pharos Production Inc. - Web3, blockchain, fintech, defi software development services](https://pharosproduction.com)

# inet-monitor
Library to display and monitor connection status

Library usage

1. Add maven repository to your gradle:
```
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```
2. In your build.gradle add :
```
  implementation 'com.github.PharosProduction:inet-monitor:1.0.0'
```
3. Register InetMonitor

```
class App : Application() {

    // Variables

    lateinit var monitor: InetMonitor

    // Life

    override fun onCreate() {
        super.onCreate()
        monitor = InetMonitor(this)
        monitor.registerReceiver()
    }
}
```

 4. In your Activity:
```
class MainActivity : AppCompatActivity(), InetMonitor.ConnectionListener {
    
    // Life

    override fun listenConnection(isConnected: Boolean) {
        if (!isConnected) {
            InetMonitor.showNotification(this)
        } else InetMonitor.hideNotification(this)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as App).monitor.addConnectionListener(this)
    }
}
```
