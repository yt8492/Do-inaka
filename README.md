# Do-inaka
dchack2019

# 設計の方針

domain層、infra層、ui層を分ける

サーバーとはgRPCで通信する

Dagger2でDIをする

`Android Architecture Components` と `Kotlin Coroutines` を使う( `ViewModel` , `LiveData` , `DataBinding` )

```
do_inaka
├── MainActivity.kt
├── domain
│   ├── model
│   └── repository
├── infra
│   ├── api
│   └── impl
└── ui

```