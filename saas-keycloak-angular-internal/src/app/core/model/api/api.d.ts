/* tslint:disable */
/* eslint-disable */
// Generated using typescript-generator version 2.32.889 on 2021-08-11 22:00:55.

export interface ClientResponse {
    id: string;
    clientId: string;
    name: string;
    rootUrl: string | null;
    baseUrl: string | null;
}

export interface MessageResponse {
    httpCode: number;
    message: string;
    errors: string[] | null;
}

export interface ObjectValidationResponse {
    valid: boolean;
    message: string;
    error: string;
}

export interface ProductRequest {
    productName: string;
    unitType: string;
}

export interface ProductResponse {
    productName: string;
    unitType: string;
    createdDate: Date;
}

export interface TenantRequest {
    namespace: string;
    timeZone: string;
}

export interface TenantResponse {
    uuid: string;
    namespace: string;
    keycloakRealm: string;
    timeZone: string;
    clients: ClientResponse[];
}

export interface UserRequest {
    firstName: string | null;
    lastName: string | null;
    email: string;
    username: string;
    password: string;
}

export interface UserResponse {
    uuid: string;
    tenantUuid: string;
    firstName: string;
    lastName: string;
    email: string;
    username: string;
    createdTimestamp: Date;
    enabled: boolean | null;
    totp: boolean | null;
    emailVerified: boolean | null;
}

export interface UserValidationResponse {
    valid: boolean;
    message: string;
    error: string;
}

export type ProductHttpResponse = "PRODUCT_CREATED" | "PRODUCT_UPDATED" | "PRODUCT_ALREADY_EXIST" | "PRODUCT_NOT_FOUND" | "PRODUCT_BAD_REQUEST" | "PRODUCT_INTERNAL_ERROR";

export type TenantHttpResponse = "TENANT_CREATED" | "TENANT_DELETED" | "TENANT_UPDATED" | "TENANT_ALREADY_EXIST" | "TENANT_NOT_FOUND" | "TENANT_NOT_ACCEPTABLE" | "TENANT_BAD_REQUEST" | "TENANT_INTERNAL_ERROR";

export type UserHttpResponse = "USER_CREATED" | "USER_DELETED" | "USER_UPDATED" | "USER_ALREADY_EXIST" | "USER_NOT_FOUND" | "USER_BAD_REQUEST" | "USER_INTERNAL_ERROR";
