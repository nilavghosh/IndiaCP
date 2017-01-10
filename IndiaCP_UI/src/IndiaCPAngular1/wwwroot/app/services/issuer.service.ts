/**
 * IndiaCP API
 * This API will drive the UI
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// /// <reference path="api.d.ts" />

/* tslint:disable:no-unused-variable member-ordering */

module app.services {
    "use strict";

    export interface IIssuerService {
        addDoc(cpProgramId: string, metadata: string, file: any, extraHttpRequestParams?: any): ng.IHttpPromise<app.models.IndiaCPProgram>;
        addISIN(cpProgramId: string, isin: string, extraHttpRequestParams?: any): ng.IHttpPromise<app.models.IndiaCPProgram>;
        fetchAllCP(cpProgramId: string, extraHttpRequestParams?: any): ng.IHttpPromise<Array<app.models.IndiaCPIssue>>;
        fetchAllCPOnThisNode(extraHttpRequestParams?: any): ng.IHttpPromise<Array<app.models.IndiaCPIssue>>;
        fetchAllCPProgram(extraHttpRequestParams?: any): ng.IHttpPromise<Array<app.models.IndiaCPProgram>>;
        fetchCP(cpIssueId: string, extraHttpRequestParams?: any): ng.IHttpPromise<app.models.IndiaCPIssue>;
        fetchCPProgram(cpProgramId: string, extraHttpRequestParams?: any): ng.IHttpPromise<app.models.IndiaCPProgram>;
        fetchCreditRating(extraHttpRequestParams?: any): ng.IHttpPromise<Array<app.models.CreditRatingDocs>>;
        issueCP(cpDetails: app.models.IndiaCPIssue, extraHttpRequestParams?: any): ng.IHttpPromise<app.models.IndiaCPIssue>;
        issueCPProgram(cpprogramDetails: app.models.IndiaCPProgram, extraHttpRequestParams?: any): ng.IHttpPromise<app.models.IndiaCPProgram>;
        issueCreditRating(creditRatingDetails: app.models.CreditRatingDocs, file: any, extraHttpRequestParams?: any): ng.IHttpPromise<app.models.CreditRatingDocs>;
    }

    class IssuerService implements IIssuerService {
        protected basePath: string;
        public defaultHeaders: any = {};

        static $inject: string[] = ["$http", "localStorageService", "Upload", "$httpParamSerializer"];

        constructor(protected $http: ng.IHttpService,
            protected localStorageService: ng.local.storage.ILocalStorageService,
            protected Upload: ng.angularFileUpload.IUploadService,
            protected $httpParamSerializer?: (d: any) => any) {
            var nodeInfo: app.models.NodeInfo = this.localStorageService.get("nodeInfo") as app.models.NodeInfo;
            var host: string = nodeInfo.host;
            var port: number = nodeInfo.port;
            this.basePath = `http://${host}:${port}/indiacp`;
        }

        private extendObj<T1, T2>(objA: T1, objB: T2) {
            for (let key in objB) {
                if (objB.hasOwnProperty(key)) {
                    objA[key.toString()] = objB[key.toString()];
                }
            }
            return <T1 & T2>objA;
        }

        /**
             * Uploads and attaches the provided document to the CPProgram on the DL
             * Uploads and attaches the provided document to the CPProgram on the DL
             * @param cpProgramId CP Program ID that uniquely identifies the CP Program issued by the Issuer
             * @param metadata Document Attachment Details
             * @param file Document Attachment
             */
        public addDoc(cpProgramId: string, metadata: string, file: any, extraHttpRequestParams?: any): ng.IHttpPromise<app.models.IndiaCPProgram> {
            const localVarPath = this.basePath + "/indiacpprogram/addDocs/{cpProgramId}"
                .replace("{" + "cpProgramId" + "}", String(cpProgramId));

            let queryParameters: any = {};
            let headerParams: any = this.extendObj({}, this.defaultHeaders);
            let formParams: any = {};

            // verify required parameter "cpProgramId" is not null or undefined
            if (cpProgramId === null || cpProgramId === undefined) {
                throw new Error("Required parameter cpProgramId was null or undefined when calling addDoc.");
            }
            // verify required parameter "metadata" is not null or undefined
            if (metadata === null || metadata === undefined) {
                throw new Error("Required parameter metadata was null or undefined when calling addDoc.");
            }
            // verify required parameter "file" is not null or undefined
            if (file === null || file === undefined) {
                throw new Error("Required parameter file was null or undefined when calling addDoc.");
            }
            headerParams["Content-Type"] = "application/x-www-form-urlencoded";

            formParams["metadata"] = metadata;

            formParams["file"] = file;

            let httpRequestParams: any = {
                method: "POST",
                url: localVarPath,
                json: false,
                data: this.$httpParamSerializer(formParams),
                params: queryParameters,
                headers: headerParams
            };

            if (extraHttpRequestParams) {
                httpRequestParams = this.extendObj(httpRequestParams, extraHttpRequestParams);
            }

            return this.$http(httpRequestParams);
        }
        /**
         * Adds the ISIN to the India CP Program
         * Adds the ISIN to the India CP Program
         * @param cpProgramId CP Program ID that uniquely identifies the CP Program issued by the Issuer
         * @param isin ISIN generated by NSDL
         */
        public addISIN(cpProgramId: string, isin: string, extraHttpRequestParams?: any): ng.IHttpPromise<app.models.IndiaCPProgram> {
            const localVarPath = this.basePath + "/indiacpprogram/addISIN/{cpProgramId}/{isin}"
                .replace("{" + "cpProgramId" + "}", String(cpProgramId))
                .replace("{" + "isin" + "}", String(isin));

            let queryParameters: any = {};
            let headerParams: any = this.extendObj({}, this.defaultHeaders);
            // verify required parameter "cpProgramId" is not null or undefined
            if (cpProgramId === null || cpProgramId === undefined) {
                throw new Error("Required parameter cpProgramId was null or undefined when calling addISIN.");
            }
            // verify required parameter "isin" is not null or undefined
            if (isin === null || isin === undefined) {
                throw new Error("Required parameter isin was null or undefined when calling addISIN.");
            }
            let httpRequestParams: any = {
                method: "POST",
                url: localVarPath,
                json: true,
                params: queryParameters,
                headers: headerParams
            };

            if (extraHttpRequestParams) {
                httpRequestParams = this.extendObj(httpRequestParams, extraHttpRequestParams);
            }

            return this.$http(httpRequestParams);
        }
        /**
         * Get CP Issues allotted under a given CP Program
         * This returns all the CP Issues under the umbrella CP Program identified by an Id provided by the call 
         * @param cpProgramId CP Program ID that uniquely identifies the CP Program issued by the Issuer
         */
        public fetchAllCP(cpProgramId: string, extraHttpRequestParams?: any): ng.IHttpPromise<Array<app.models.IndiaCPIssue>> {
            const localVarPath = this.basePath + "/fetchAllCP/{cpProgramId}"
                .replace("{" + "cpProgramId" + "}", String(cpProgramId));

            let queryParameters: any = {};
            let headerParams: any = this.extendObj({}, this.defaultHeaders);
            // verify required parameter "cpProgramId" is not null or undefined
            if (cpProgramId === null || cpProgramId === undefined) {
                throw new Error("Required parameter cpProgramId was null or undefined when calling fetchAllCP.");
            }
            let httpRequestParams: any = {
                method: "GET",
                url: localVarPath,
                json: true,
                params: queryParameters,
                headers: headerParams
            };

            if (extraHttpRequestParams) {
                httpRequestParams = this.extendObj(httpRequestParams, extraHttpRequestParams);
            }

            return this.$http(httpRequestParams);
        }
        /**
         * Get All Open CP Issues for the given Issuer/Investor. Open CP Issues refers to the Issues that are yet to mature
         * This returns all the Open CP Issues for the given DL Node
         */
        public fetchAllCPOnThisNode(extraHttpRequestParams?: any): ng.IHttpPromise<Array<app.models.IndiaCPIssue>> {
            const localVarPath = this.basePath + "/fetchAllCP";

            let queryParameters: any = {};
            let headerParams: any = this.extendObj({}, this.defaultHeaders);
            let httpRequestParams: any = {
                method: "GET",
                url: localVarPath,
                json: true,
                params: queryParameters,
                headers: headerParams
            };

            if (extraHttpRequestParams) {
                httpRequestParams = this.extendObj(httpRequestParams, extraHttpRequestParams);
            }

            return this.$http(httpRequestParams);
        }
        /**
         * Fetch All CPPrograms for the current Issuer. The current Issuer is assumed from the DL Node that this request is forwarded to.
         * Returns all the CP Programs for the current issuer 
         */
        public fetchAllCPProgram(extraHttpRequestParams?: any): ng.IHttpPromise<Array<app.models.IndiaCPProgram>> {
            const localVarPath = this.basePath + "/indiacpprogram/fetchAllCPProgram";

            let queryParameters: any = {};
            let headerParams: any = this.extendObj({}, this.defaultHeaders);
            let httpRequestParams: any = {
                method: "GET",
                url: localVarPath,
                json: true,
                params: queryParameters,
                headers: headerParams
            };

            if (extraHttpRequestParams) {
                httpRequestParams = this.extendObj(httpRequestParams, extraHttpRequestParams);
            }

            return this.$http(httpRequestParams);
        }
        /**
         * Get CP Issue for the given Issuer/Investor
         * This returns the CP Issue identified by an Id provided by the call 
         * @param cpIssueId Unique identifier of the CP Issue to be fetched
         */
        public fetchCP(cpIssueId: string, extraHttpRequestParams?: any): ng.IHttpPromise<app.models.IndiaCPIssue> {
            const localVarPath = this.basePath + "/fetchCP/{cpIssueId}"
                .replace("{" + "cpIssueId" + "}", String(cpIssueId));

            let queryParameters: any = {};
            let headerParams: any = this.extendObj({}, this.defaultHeaders);
            // verify required parameter "cpIssueId" is not null or undefined
            if (cpIssueId === null || cpIssueId === undefined) {
                throw new Error("Required parameter cpIssueId was null or undefined when calling fetchCP.");
            }
            let httpRequestParams: any = {
                method: "GET",
                url: localVarPath,
                json: true,
                params: queryParameters,
                headers: headerParams
            };

            if (extraHttpRequestParams) {
                httpRequestParams = this.extendObj(httpRequestParams, extraHttpRequestParams);
            }

            return this.$http(httpRequestParams);
        }
        /**
         * Get CP Program details by Id. The current Issuer is assumed from the DL Node that this request is forwarded to.
         * This returns a single CP Program identified by an Id provided by the call 
         * @param cpProgramId CP Program ID that uniquely identifies the CP Program issued by the Issuer
         */
        public fetchCPProgram(cpProgramId: string, extraHttpRequestParams?: any): ng.IHttpPromise<app.models.IndiaCPProgram> {
            const localVarPath = this.basePath + "/indiacpprogram/fetchCPProgram/{cpProgramId}"
                .replace("{" + "cpProgramId" + "}", String(cpProgramId));

            let queryParameters: any = {};
            let headerParams: any = this.extendObj({}, this.defaultHeaders);
            // verify required parameter "cpProgramId" is not null or undefined
            if (cpProgramId === null || cpProgramId === undefined) {
                throw new Error("Required parameter cpProgramId was null or undefined when calling fetchCPProgram.");
            }
            let httpRequestParams: any = {
                method: "GET",
                url: localVarPath,
                json: true,
                params: queryParameters,
                headers: headerParams
            };

            if (extraHttpRequestParams) {
                httpRequestParams = this.extendObj(httpRequestParams, extraHttpRequestParams);
            }

            return this.$http(httpRequestParams);
        }
        /**
         * Get current active Credit Rating details
         * This returns only the current active credit rating details
         */
        public fetchCreditRating(extraHttpRequestParams?: any): ng.IHttpPromise<Array<app.models.CreditRatingDocs>> {
            const localVarPath = this.basePath + "/creditrating/fetchCreditRating";

            let queryParameters: any = {};
            let headerParams: any = this.extendObj({}, this.defaultHeaders);
            let httpRequestParams: any = {
                method: "GET",
                url: localVarPath,
                json: true,
                params: queryParameters,
                headers: headerParams
            };

            if (extraHttpRequestParams) {
                httpRequestParams = this.extendObj(httpRequestParams, extraHttpRequestParams);
            }

            return this.$http(httpRequestParams);
        }
        /**
         * Issue new CP under a CPProgram
         * This creates a new CP with the details provided
         * @param cpDetails Details of the CP to be Issued
         */
        public issueCP(cpDetails: app.models.IndiaCPIssue, extraHttpRequestParams?: any): ng.IHttpPromise<app.models.IndiaCPIssue> {
            const localVarPath = this.basePath + "/indiacpissue/issueCP";

            let queryParameters: any = {};
            let headerParams: any = this.extendObj({}, this.defaultHeaders);
            // verify required parameter "cpDetails" is not null or undefined
            if (cpDetails === null || cpDetails === undefined) {
                throw new Error("Required parameter cpDetails was null or undefined when calling issueCP.");
            }
            let httpRequestParams: any = {
                method: "POST",
                url: localVarPath,
                json: true,
                data: cpDetails,
                params: queryParameters,
                headers: headerParams
            };

            if (extraHttpRequestParams) {
                httpRequestParams = this.extendObj(httpRequestParams, extraHttpRequestParams);
            }

            return this.$http(httpRequestParams);
        }
        /**
         * Issue new CP Program
         * This creates a new CP Program with the details provided
         * @param cpprogramDetails Details of the CP Program to be Issued
         */
        public issueCPProgram(cpprogramDetails: app.models.IndiaCPProgram, extraHttpRequestParams?: any): ng.IHttpPromise<app.models.IndiaCPProgram> {
            const localVarPath = this.basePath + "/indiacpprogram/issueCPProgram";

            let queryParameters: any = {};
            let headerParams: any = this.extendObj({}, this.defaultHeaders);
            // verify required parameter "cpprogramDetails" is not null or undefined
            if (cpprogramDetails === null || cpprogramDetails === undefined) {
                throw new Error("Required parameter cpprogramDetails was null or undefined when calling issueCPProgram.");
            }
            let httpRequestParams: any = {
                method: "POST",
                url: localVarPath,
                json: true,
                data: cpprogramDetails,
                params: queryParameters,
                headers: headerParams
            };

            if (extraHttpRequestParams) {
                httpRequestParams = this.extendObj(httpRequestParams, extraHttpRequestParams);
            }

            return this.$http(httpRequestParams);
        }
        /**
         * Uploads the Credit Rating Document and Creates a Smart Contract for the same on the DL to establish immutable golden copy
         * Uploads the Credit Rating Document and Creates a Smart Contract for the same on the DL to establish immutable golden copy
         * @param creditRatingDetails Credit Rating Details for creating the Smart Contract
         * @param file Document Attachment
         */
        public issueCreditRating(creditRatingDetails: app.models.CreditRatingDocs, file: any, extraHttpRequestParams?: any): ng.IHttpPromise<app.models.CreditRatingDocs> {
            const localVarPath = this.basePath + "/creditrating/issueCreditRating";

            let queryParameters: any = {};
            let headerParams: any = this.extendObj({}, this.defaultHeaders);
            let formParams: any = {};

            // verify required parameter "creditRatingDetails" is not null or undefined
            if (creditRatingDetails === null || creditRatingDetails === undefined) {
                throw new Error("Required parameter creditRatingDetails was null or undefined when calling issueCreditRating.");
            }
            // verify required parameter "file" is not null or undefined
            if (file === null || file === undefined) {
                throw new Error("Required parameter file was null or undefined when calling issueCreditRating.");
            }
            headerParams["Content-Type"] = "application/x-www-form-urlencoded";
            formParams["creditRatingDetails"] = creditRatingDetails;

            formParams["file"] = file;

            let httpRequestParams: any = {
                method: "POST",
                url: localVarPath,
                json: false,
                data: this.$httpParamSerializer(formParams),
                params: queryParameters,
                headers: headerParams
            };

            if (extraHttpRequestParams) {
                httpRequestParams = this.extendObj(httpRequestParams, extraHttpRequestParams);
            }

            let httpUploadRequestParams:any = {
                url: localVarPath,
                data: { file: file, creditRatingDetails: this.Upload.jsonBlob(creditRatingDetails) },
                method: "POST"
            };

            return this.Upload.upload(httpUploadRequestParams);

            //return this.$http(httpRequestParams);
        }
    }

    angular
        .module("app.services")
        .service("app.services.IssuerService", IssuerService);
}
