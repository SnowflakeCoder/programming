## Problem: 

**Design rate limiter** — A rate limiter is a tool that <u>monitors the number of requests per</u>
<u>a window time</u> a service agrees to allow. If the request count exceeds the number agreed by the
service owner and the user (in a decided window time), the rate limiter <u>blocks all the excess</u>
<u>calls (say by throwing exceptions)</u>. The user can be a human or any other service (ex: in a micro service-based architecture)

## Objectives

• <u>Different APIs would have different rate limits</u>
• Should be possible to <u>set default limits</u>. This will be applied when an API specific limit
has not been configured.
• The solution should consider <u>rate limiting based on User+API combination</u>
• The solution should be plug and play (easily configurable)
• Production ready code (Automated tests are mandatory)

------

------

## Deliverables

• Completely working solution with Java source code
• Upload on github and share link.
• Meaningful readme file
• Any dependencies required must be highlighted.
• Any assumptions made must be clearly documented.

## How is the solution evaluated?

1. Correctness – How correct is the solution? Does it handle all the requirements
appropriately?
2. Code Quality – As you are the sole owner of the implementation, request you to not take
the code quality aspects extremely seriously.
3. Plug and Play Integration capabilities – Self-explanatory

------

------

## API Documentation

### RateLimit APIs

#### Create API

```typescript
curl --location --request POST 'localhost:8080/rateLimits' \
--header 'Content-Type: application/json' \
--data-raw ' {
  "rateLimitId" : {
    "apiName" : "DEFAULT",
    "clientId" : "DEFAULT"  
  },   
  "timeUnit" : "MINUTES",
  "timeValue" : "1",
  "maxPermits" : "3"
 }'
```

#### GetALL API

```typescript
curl --location --request GET 'localhost:8080/rateLimits' \
--header 'Content-Type: application/json' \
--data-raw ' {
  "timeUnit" : "MINUTES",
  "timeValue" : "2",
  "maxPermits" : "3",
  "apiName" : "DEFAULT",
  "clientId" : "DEFAULT"  
 }'
```

#### GetByID

**Note**: This is implemented as a composite primary key hence used POST instead of GET

```typescript
curl --location --request POST 'localhost:8080/getRateLimits' \
--header 'Content-Type: application/json' \
--data-raw '{
    "apiName" : "DEFAULT",
    "clientId" : "DEFAULT"  
}'
```

#### Update API

```typescript
curl --location --request PUT 'localhost:8080/rateLimits' \
--header 'Content-Type: application/json' \
--data-raw ' {
  "rateLimitId" : {
    "apiName" : "DEFAULT",
    "clientId" : "DEFAULT"  
  },   
  "timeUnit" : "MINUTES",
  "timeValue" : "10",
  "maxPermits" : "30"
 }'
```

#### Delete API

```typescript
curl --location --request DELETE 'localhost:8080/rateLimits' \
--header 'Content-Type: application/json' \
--data-raw '{
    "apiName" : "DEFAULT",
    "clientId" : "DEFAULT"  
}'
```

------

------

### Main APIs

```typescript
curl --location --request GET 'localhost:8080/main/testApi1' \
--header 'Content-Type: application/json' \
--data-raw ' {"name" : "Arun"}'
```

**Response**

- 429, Too Many Requests => (No Default RateLimit API)

- 200, TestApi1 Success  => after adding Default Ratelimit config as below.

  - ```javascript
     {
      "rateLimitId" : {
        "apiName" : "DEFAULT",
        "clientId" : "DEFAULT"  
      },   
      "timeUnit" : "MINUTES",
      "timeValue" : "1",
      "maxPermits" : "3"
     }
    ```

  - ```javascript
    //localhost:8080/rateLimits after first API call, note values of allowedPermits and intervalStartTime
    [
        {
            "rateLimitId": {
                "apiName": "DEFAULT",
                "clientId": "DEFAULT"
            },
            "timeUnit": "MINUTES",
            "timeValue": 1,
            "maxPermits": 3,
            "intervalStartTime": 1600600594081,
            "allowedPermits": 1
        }
    ]
    ```



------

------

## Assumptions

- Used "**DEFAULT**" as the default value for API name and ClientId

- Update call (PUT) will reset values of intervalStartTime and allowedPermits.

- RateLimit API is only applied to Main API (**/main/***) not to RateLimit Rest APIs. Please see class RateLimitWebConfig.java

- By default no RateLimit configs are added so all Main API methods give 429, Too Many Requests.

- Used **Spring H2 repository** (in-memory database) so all configs will reset after application restart.

- RateLimit Logic

  - ```typescript
    if(currentTime > IntervalStartTime + rateLimitTimeDuration) 
    {	//interval expired so set allowedPermits = 1 and set IntervalStartTime
    	allowRequest = rateLimit.resetInterval(currentTime);
    }
    else {
        //increment allowedPermits if allowedPermits < maxPermits
    	allowRequest = rateLimit.incrementAllowedPermits();
    }
    ```



