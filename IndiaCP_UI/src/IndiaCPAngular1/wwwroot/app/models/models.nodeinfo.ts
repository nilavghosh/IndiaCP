﻿module app.models {
    "use strict";

    export class NodeInfo {
        constructor(public nodeType?: string,
            public host?: string,
            public port?: number,
            public nodeName?: string,
            public dlNodeName?: string) {
        }
    }

}