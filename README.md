# cljs-asciidoctor

## Foreign JS Files

[http://clojurescript.org/guides/webpack](http://clojurescript.org/guides/webpack)
build foreign js files with webpack

```
npm install

```
Webpack configuration: see webpack.config.js
index_bundle.js will be created in ./dist
from ./src/js/index.js

```
npx webpack

```
config in appropriate file

```
:npm-deps false
:foreign-libs [{:file "dist/index_bundle.js"
                :provides ["asciidoctor"]
                :global-exports {asciidoctor Asciidoctor}}]
```

## Development Mode

### Start Cider from Emacs:

Put this in your Emacs config file:

```
(setq cider-cljs-lein-repl
	"(do (require 'figwheel-sidecar.repl-api)
         (figwheel-sidecar.repl-api/start-figwheel!)
         (figwheel-sidecar.repl-api/cljs-repl))")
```

Navigate to a clojurescript file and start a figwheel REPL with `cider-jack-in-clojurescript` or (`C-c M-J`)

### Compile application

```
lein fig:build
```

### Run application:



Figwheel will automatically push cljs changes to the browser.

Wait a bit, then browse to [http://localhost:3500](http://localhost:3500).

