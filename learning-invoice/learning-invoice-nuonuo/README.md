# 诺诺开放平台

## 快速入门

在接入诺诺开放平台（open.nuonuo.com）能力前，首先要成为诺诺网开放平台用户。

![img](https://open.jss.com.cn/image/guide.79e934f5.png)

### 企业账号注册

企业需使用所在企业税号注册成为诺诺网企业用户，注册时需填写企业真实名称、验证手机号码、设置密码。若企业注册税号不真实，将影响后续接口调用。若所在企业税号已注册，可直接登录诺诺开放平台。

![img](https://open.jss.com.cn/image/register.f2582032.png)

### 企业入驻

#### 1.企业身份选择

平台提供系统服务商、独立企业两种身份供企业选择。具体请参考[入驻身份说明](https://open.jss.com.cn/#/dev-doc/identity)

![img](https://open.jss.com.cn/image/select-role.0ab2fc5f.png)

#### 2.企业信息录入

企业需提供营业执照原件或彩色扫描件照片、公司主要业务能力和客户所属行业等信息，并简述合作需求。

![img](https://open.jss.com.cn/image/init-company.1c353b98.png)

#### 3.联系人信息录入

企业需如实配置联系人类型，提供联系人姓名、手机号、电子邮箱等信息，并上传联系人身份证件照片。平台公告、SDK及接口版本更新等将采用站内信、短信、邮件形式通知到联系人，请确保联系信息真实有效。

![img](https://open.jss.com.cn/image/init-contact1.7bd91ed3.png)

如若联系人非企业法人，还需下载《诺诺开放平台企业资质认证授权委托书》模版，填写申请人信息并加盖企业公章后拍照或扫描上传。

![img](https://open.jss.com.cn/image/init-contact2.8527d436.png)

#### 4.企业资质审核

企业成功提交企业资质信息后，平台将尽快完成审核，审核结果将通过站内信、短信、邮件形式告知。

### 创建应用

企业资质审核通过后，企业可在平台应用管理页面创建应用并接入平台相关能力。具体请参考[应用创建](https://open.jss.com.cn/#/dev-doc/create-app)

![img](https://open.jss.com.cn/image/create-app.d5e0432b.jpg)

### 开发调试

诺诺开放平台提供沙箱环境（仿真业务环境）完成应用接口调试。

### 发布上线

应用开发测试完成后，企业暂不需要提交至诺诺开放平台进行上线。3个月内未使用的应用会被系统删除。

## 入驻身份说明

目前平台提供系统服务商、独立企业两种身份选择（暂不支持自研开发者身份选择），请按照实际情况自行选择。

### 系统服务商

即有资质申请接入诺诺开放平台业务能力（含API、服务等），可开发第三方应用为其他无开发能力的商户或企业提供系统服务，并享有多套服务方案及优惠政策的企业用户。后期可支持系统服务商将应用发布至诺诺服务市场，商户可直接在线自助订购应用或服务。

![img](https://open.jss.com.cn/image/select-service.68318f18.png)

### 独立企业

即有资质申请接入诺诺开放平台业务能力（含API、服务等），为自己公司开发应用，直接为用户提供服务的企业用户，可支持拓展为系统服务商身份（平台审核通过后身份生效）。

![img](https://open.jss.com.cn/image/select-self.d4b103e9.png)

## 创建应用

开发者在创建应用前，需要先选择应用类型。诺诺开放平台支持如下两种应用类型，企业可根据业务需求选择：

### 使用场景

接入诺诺开放平台业务能力，为自己公司开发应用。自助接入建议使用自用型应用。

### 业务模式图

![img](https://open.jss.com.cn/image/auth-process.a8483d65.png)

### 创建应用

#### 1.企业成功登录并认证通过后，可转至[应用管理](https://open.jss.com.cn/#/app-manage/app-list)页面创建自用型应用

注：应用标签可根据所需调用的API进行选择，一个应用标签最多可创建三个应用；应用创建成功后，应用标签和应用类型不支持变更。

#### 2.应用创建成功后，即可在“应用概况”页面获得一个appKey和一个appSecret

注：appKey是每个应用的唯一身份，appSecret是应用里面用于保障数据安全的“钥匙”，每一个应用都有一个独立的访问密钥，为了保证数据的安全，appKey、appSecret务必不能泄漏。

### 获取access_token

access_token是开发者调用开放平台接口的调用凭据，也叫做令牌(access_token)
。开发者通过应用参数向诺诺开放平台调用令牌接口地址获取access_token。令牌有效期默认24h（也可在创建应用时设置token永不过期），且令牌30天内的调用上限为50次
，请开发者做好令牌的管理。

通过下面步骤开发者得到access_token之后将可以使用平台相关API。具体参见诺诺开放平台[API](https://open.jss.com.cn/#/api-doc/common-api)

开发者通过应用appKey和appSecret获取access_token。调用方法参见[SDK中心](https://open.jss.com.cn/#/dev-doc/sdk-usage?anchorId=sdk-java-1)

#### 1. 请求地址

https://open.nuonuo.com/accessToken

#### 2. 请求方式

POST

请求头中Content-Type需配置为application/x-www-form-urlencoded;charset=UTF-8

#### 3. 请求参数

| 参数          | 是否必须 | 说明                                     |
| :------------ | :------- | :--------------------------------------- |
| client_id     | yes      | 填写应用的appKey                         |
| client_secret | yes      | 填写应用的appSecret                      |
| grant_type    | yes      | 授权类型，此值固定为“client_credentials” |

注： 此接口不允许频繁调用，请妥善保存access_token。

#### 4. 响应参数

| 参数         | 类型   | 说明                                      |
| :----------- | :----- | :---------------------------------------- |
| access_token | String | 接口请求唯一身份令牌                      |
| expires_in   | String | access_token 的过期时长，24小时（单位秒） |

注：自用型应用无令牌刷新机制。令牌（access_token）有效期为24小时，即将到期时（比如23小时）开发者需重新调用授权接口获取一个新的access_token，截止时间重新计算。若创建应用时设置token为永久有效则无需刷新。

#### 5. 响应示例

{"access_token": "54871544ED15874564115","expires_in": "86400"}

#### 6. 异常示例

{"error":"070307","error_description":"Incorrect appType"}

### 业务异常码

| 异常编码 | 异常                           | 描述                                                         |
| :------- | :----------------------------- | :----------------------------------------------------------- |
| 070304   | Incorrect clientId[xxxx]       | 请求参数clientId不正确                                       |
| 070305   | Incorrect clientSecret[xxxx]   | 请求参数clientSecret不正确                                   |
| 070306   | The number of calls is limited | 超过调用次数上限(30天内限制请求*50*次)                       |
| 070307   | Incorrect appType[xxxx]        | 应用类型错误，请走对应的授权流程                             |
| 070315   | Incorrect content-type         | 请设置正确的content-type， 如application/x-www-form-urlencoded;charset=UTF-8 |

## 沙箱环境使用

### 一、沙箱说明

诺诺网沙箱环境是诺诺网开放平台提供给独立企业、系统服务商开发者的测试环境。

该沙箱环境模拟线上真实的业务环境，帮助开发者完成应用的调试。

开发者可以在沙箱环境完全仿真的测试诺诺网开放平台的接口。（[前往API调试](https://open.jss.com.cn/#/dev-doc/api-debugging)）

### 二、沙箱使用

\1. 开发者在诺诺网开放平台成功创建应用后，平台会自动为开发者生成沙箱环境的测试身份。

![img](https://open.jss.com.cn/image/sandbox.1e9719d7.png)

\2. 开发者通过沙箱环境获取到诺诺开放平台赋予开发者的新的测试身份appKey和appSecret。（服务商应用沙箱回调url可以和线上一致）

\3. 开发者根据新的测试身份，完成自用型应用（[独立企业接入](https://open.jss.com.cn/#/dev-doc/create-app?type=self)
）或者第三方应用（[系统服务商接入](https://open.jss.com.cn/#/dev-doc/create-app?type=service)）接入流程。

\4. 开发者通过接入流程拿到的access_token为测试环境的身份令牌，线上环境调用时注意切换。

5.沙箱环境频控次数是正式环境的1/10，但会保证开发者至少有10r/s的访问频率，请开发者根据自身情况适当调用测试。

\6. 第三方应用通过授权登录获取access_token前需要在商户管理页面添加所服务的商户。添加成功的商户可以成功授权。

![img](https://open.jss.com.cn/image/new-business-list.05f56ca9.png)

### 三、沙箱地址

沙箱环境地址：https://sandbox.nuonuocs.cn/open/v1/services

查看 [API数据>>](https://open.jss.com.cn/#/api-doc/common-api)

注：开发者调用API时需要在消息头请求参数上填写userTax参数（测试税号）。正式环境下自用型应用可不填写此值。

### 四、温馨提示

诺诺开放平台会把沙箱环境和线上环境同步，但目前无法保证数据全部相同。开发者测试时遇到数据返回为空属于正常现象，不影响正常的API测试。请大家使用时注意。

## 子账号管理

主账号（即诺诺网企业账号）是诺诺开放平台资源对接和计量计费的基本主体。主账号默认拥有其名下平台资源的完全操作和访问权限，包括应用管理、消费明细管理、商户管理等。默认情况下，平台资源只能被主账号所使用，任何其他用户访问都需要获得主账号的授权。

子账号管理是诺诺开放平台提供给主账号添加并关联实体（子账号，即主账号所对应企业税号下的员工个人账号）的特有功能。子账号默认不拥有资源使用权限，必须由所属主账号进行授权，授权后的子账号享有主账号为其分配的诺诺开放平台相关操作和访问权限，可协助主账号管理平台相关业务。

### 添加并邀请子账号

#### 1.添加子账号

企业资质认证通过的企业用户，可前往“个人中心—子账号管理”页面添加子账号。被添加的子账号必须为当前企业下的员工个人账号，否则先提醒员工前往诺诺网用户中心 ( [u.nuonuo.com](https://u.nuonuo.com/) )
注册个人账号。

![img](https://open.jss.com.cn/image/list.97fd38fa.png)

![img](https://open.jss.com.cn/image/add-account.930a3cb4.png)

#### 2. 邀请子账号

子账号添加邀请发出后，平台将通过站内信、短信、邮件形式通知子账号处理邀请。接受邀请后，子账号将享有主账号为其分配的诺诺开放平台相关操作和访问权限。子账号邀请链接有效期为2小时，链接失效后需主账号重新发送邀请链接。

![img](https://open.jss.com.cn/image/invite.fe0b5e02.png)

### 移除子账号

针对子账号员工离职、调动等场景，主账号可移除相应子账号，移除成功后主账号授予的访问和操作权限立即失效。

![img](https://open.jss.com.cn/image/remove.ef204ba8.png)

### 子账号权限设置

主账号可定期维护子账号操作和访问权限，仅赋予权限的菜单子账号可见，仅赋予权限的操作子账号可编辑。

![img](https://open.jss.com.cn/image/auth.7eb29797.png)

## API调试工具使用

API调试工具是诺诺开放平台基于已开放接口提供的在线调试工具。若您首次了解或对接诺诺开放平台，此工具可帮助您了解平台API的调用原理；若您已接入诺诺开放平台API，此工具可提供沙箱环境进行接口调试。[进入API调试工具>>](https://open.jss.com.cn/#/dev-doc/api-debugging)

注：1. API调试工具仅支持已认证的企业账号或已认证企业下子账号登录使用；2.
API调试工具仅支持诺诺发票标签下已开通的接口使用，请先前往应用管理创建“诺诺发票”标签的应用，并开通此标签下的应用接口。

### 1.调试接口选择

#### 前往“帮助支持—常用工具”或“文档中心—开发文档—常用工具”页面，选择需要进行沙箱调试的接口API编码。

![img](https://open.jss.com.cn/image/debugging1.70440db7.png)

### 2.公共请求参数配置

#### 沙箱环境调试接口时，appKey、appSecret由系统自动生成，税号建议采用339901999999142。

![img](https://open.jss.com.cn/image/debugging2.8dc13494.png)

### 3.私有请求参数配置

#### 根据被调试接口的必填属性，配置相关私有请求参数，并发送接口调用请求。

![img](https://open.jss.com.cn/image/debugging3.0777422f.png)

### 4.在线调试接口

#### 根据接口响应信息，对照接口异常信息进行API在线调试。

![img](https://open.jss.com.cn/image/debugging4.3ad86cd7.png)

## SDK中心

### 开发语言

-

- Java SDK*v1.0.5.2*

- 最近更新：2022-1-11

- 下载最新版SDK

### 运行环境

Java SDK工具包适用于JDK1.7及以上环境。

### 安装SDK

#### 通过Maven安装（推荐）

通过 Maven 获取安装是使用 Java SDK 的推荐方法，Maven 是 Java 的依赖管理工具，支持您项目所需的依赖项，并将其安装到项目中。为您的项目添加
Maven 依赖项，只需在 Maven pom.xml 添加以下依赖项即可：

```xml

<dependency>
    <groupId>com.nuonuo</groupId>
    <artifactId>open-sdk</artifactId>
    <version>1.0.5.2</version>
</dependency>
```

注意：上图为示例代码，请[获取](https://mvnrepository.com/artifact/com.nuonuo/open-sdk)最新依赖项信息。

#### 通过源码包安装

1、下载最新版[Java SDK](https://open.jss.com.cn/api/interplatform/sdkDownLoad.do?fid=3232300437-4b121b54c26041188de42d80b7f6e89f)
压缩工具包。

2、将下载的压缩工具包解压后，复制到工程文件夹中。

3、在Eclipse右键“工程 -> Properties -> Java Build Path -> Add JARs”。

4、添加SDK工具包和第三方依赖工具包。

### SDK集成示例

#### 自用型应用获取令牌

执行下述方法（具体参数说明可参见[独立企业接入](https://open.jss.com.cn/#/dev-doc/create-app?type=self&anchorId=dev-self-token)
）

```java
String json=NNOpenSDK.getIntance().getMerchantToken("appKey","appSecret");
```

#### 第三方应用获取令牌

执行下述方法（具体参数说明可参见[系统服务商接入](https://open.jss.com.cn/#/dev-doc/create-app?type=service&anchorId=dev-service-token)
）

```java
String json=NNOpenSDK.getIntance().getISVToken("appKey","appSecret","code","taxnum","redirectUri");
```

#### 第三方应用刷新令牌

执行下述方法（具体参数说明可参见[系统服务商接入](https://open.jss.com.cn/#/dev-doc/create-app?type=service&anchorId=dev-service-refresh)
）

```java
String json=NNOpenSDK.getIntance().refreshISVToken("refreshToken","userId","appSecret");
```

#### 发送报文请求

请求示例

```java
NNOpenSDK sdk=NNOpenSDK.getIntance();
        String taxnum="23***789";//ISV下授权商户税号，自用型应用置""即可
        String appKey="Hn***XL";
        String appSecret="F65***65F";
        String method="nuonuo.***";//API方法名
        String token="2d484e**************pdui";//访问令牌
        String content="{\"city\":\"广州市\",  …,  \"maxid\":\"0\"}";//API私有请求参数
        String url="https://sdk.nuonuo.com/open/v1/services"; //SDK请求地址
        String senid=UUID.randomUUID().toString().replace("-",""); // 唯一标识，由企业自己生成32位随机码
        String json=sdk.sendPostSyncRequest(url,senid,appKey,appSecret,token,taxnum,method,content); // 请求API
```

响应示例

```java
{"code":"200","describe":"调用成功","result":"****"}
```

## 公共异常码

开放平台会对开发者的接口非业务调用错误做统一处理，返回码如下：

| 异常代码                           | 描述                                                         | 处理建议                                                     |
| :--------------------------------- | :----------------------------------------------------------- | :----------------------------------------------------------- |
| 0200                               | API签名异常                                                  | 服务异常，请联系诺诺开放平台管理员                           |
| 0201                               | 必填项输入参数空                                             | 请确认必填字段是否不为空                                     |
| 输入参数格式有误                   | 请确认数据格式是否填写正确                                   |                                                              |
| API授权异常                        | 服务异常，请联系诺诺开放平台管理员                           |                                                              |
| 客户端传输格式与解析格式描述不相符 | 请确认消息头dataType指定数据格式是否与请求数据格式匹配       |                                                              |
| 0202                               | API计费异常                                                  | 服务异常，请联系诺诺开放平台管理员                           |
| 0203                               | API处理异常                                                  | 服务异常，请联系诺诺开放平台管理员                           |
| 040102                             | API服务异常，请稍后重试                                      | 服务异常，请联系诺诺开放平台管理员                           |
| 05                                 | 请求编码异常                                                 | URLDecoder解码异常                                           |
| 0701                               | 解密异常                                                     | 请检查appKey是否填写正确                                     |
| 070101                             | 密钥空                                                       | 请检查appKey是否填写正确                                     |
| 获取app密钥失败或appkey无效        | 请检查appKey是否填写正确                                     |                                                              |
| 070102                             | 密钥长度不够                                                 | 请确认appSecret填写正确                                      |
| 070103                             | 无法识别加密算法                                             | 请确认消息头signMethod值是否正确，目前仅支持AES              |
| 070201                             | 超出API日流量限额                                            | API当日免费调用次数超出限制，请联系诺诺开放平台运营增加调用次数（免费API） |
| 070204                             | 当前接口无剩余流量，请前往应用概况页面增加接口调用量         | API剩余调用次数已用完，请自行在平台上购买（收费API）         |
| 070301                             | accessToken不匹配/或appKey不匹配                             | 请检查accessToken，appKey是否填写正确及匹配                  |
| 070302                             | APP未申请该资源                                              | 请联系诺诺开放平台运营开通API权限                            |
| 请联系服务商开通API权限            | 请联系服务商开通API权限                                      |                                                              |
| 请联系服务商调整API使用期限        | 请联系服务商调整API使用期限                                  |                                                              |
| 请联系服务商调整API日流量限额      | 请联系服务商调整API日流量限额                                |                                                              |
| 0704                               | 解压异常                                                     | 请确认是否使用GZIP压缩及压缩、数据加密和URL编码顺序是否正确  |
| 压缩方式无法识别                   | 请确认消息头compress字段是否填写正确，目前仅支持GZIP         |                                                              |
| 070401                             | 调用用户身份不匹配                                           | 请检查商户税号taxnum和token是否匹配                          |
| 070501                             | 请求的API不存在                                              | 请检查API编码是否填写正确，再次重试                          |
| 070601                             | 密钥获取失败                                                 | 请检查appKey是否填写正确                                     |
| 签名不匹配                         | 请使用诺诺官方SDK调用API，尽量保证服务器编码为UTF-8，如果是Windows服务器默认编码为GBK，可以通过SDK中的convertToUtf8方法转换报文体，如body = sdk.convertToUtf8(body); |                                                              |
| 验签失败                           | 请检查服务器时间是否与北京时间保持一致                       |                                                              |