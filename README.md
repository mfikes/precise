# precise

Tagged literals for [exact](https://github.com/gfredericks/exact).

## Usage

```
cljs.user=> #exact/integer "2319871239717122424323214112455623"
#exact/integer "2319871239717122424323214112455623"
cljs.user=> #exact/ratio "2/3"
#exact/ratio "2/3"
cljs.user=> (exact/numerator *1)
#exact/integer "2"
```

Newtonian square root [example](https://github.com/gfredericks/exact/blob/master/README.md#usage):

```
my.namespace=> epsilon
#exact/ratio "1/1000000000000000000000000"
my.namespace=> TWO
#exact/integer "2"
my.namespace=> (newtonian-square-root TWO epsilon)
#exact/ratio "1572584048032918633353217/1111984844349868137938112"
```
