# Express Kassa

В проекте используются: 
  * Spring Boot, 
  * Spring Security, 
  * Spring Data JPA

Примеры основных запросов:

  * Регистрация:
  
    curl --location --request POST 'http://localhost:8080/auth/register' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "email": "ahlypalo123@gmail.com",
        "password": "123456"
    }'
    
  * Авторизация:
  
    curl --location --request POST 'http://localhost:8080/auth/login' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "email": "ahlypalo123@gmail.com",
        "password": "123456"
    }'
    
  * Добавление продукта:
  
    curl --location --request POST 'http://localhost:8080/product' \
    --header 'Authorization: eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhaGx5cGFsbzEyM0BnbWFpbC5jb20iLCJpYXQiOjE2NDQyNTEwMTZ9.C869yRdrXIiZKkl_tS5YA-9ZM143w7_CRt0Ai35evcQG7wyTZ-3c_5xzf_iyU2CpDvgwZuZUIU41DMZY6H_nRA' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "name": "Капучино",
        "price": "120",
        "barCode": "123456",
        "photoUrl": "http://localhost:8080/user-photos/123-123-123.jpg",
    }'
    
  * Получение списка продуктов:
  
    curl --location --request GET 'http://localhost:8080/product' \
    --header 'Authorization: eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhaGx5cGFsbzEyM0BnbWFpbC5jb20iLCJpYXQiOjE2NDQyNTEwMTZ9.C869yRdrXIiZKkl_tS5YA-9ZM143w7_CRt0Ai35evcQG7wyTZ-3c_5xzf_iyU2CpDvgwZuZUIU41DMZY6H_nRA'
  
  Пример ответа:
  
    [
        {
            "id": 1,
            "name": "Капучино",
            "price": 120.00,
            "barCode": "123456",
            "amount": null,
            "photoUrl": "http://localhost:8080/user-photos/123-123-123.jpg",
            "merchant": {
                "id": 3,
                "email": "ahlypalo123@gmail.com",
                "details": null
            }
        }
    ]
    
  * Добавление чека:
  
    curl --location --request POST 'http://localhost:8080/check' \
    --header 'Authorization: eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhaGx5cGFsbzEyM0BnbWFpbC5jb20iLCJpYXQiOjE2NDQyNTEwMTZ9.C869yRdrXIiZKkl_tS5YA-9ZM143w7_CRt0Ai35evcQG7wyTZ-3c_5xzf_iyU2CpDvgwZuZUIU41DMZY6H_nRA' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "date": 11111111111,
        "products": [
            {
                "id": 1,
                "name": "Капучино",
                "price": 120.00,
                "barCode": "123456",
                "photoUrl": "http://localhost:8080/user-photos/123-123-123.jpg"
            }
        ],
        "total": "240"
    }'
    
  * Открытие смены:
    
      curl --location --request POST 'http://localhost:8080/shift' \
      --header 'Authorization: eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhaGx5cGFsbzEyM0BnbWFpbC5jb20iLCJpYXQiOjE2NDQyNTEwMTZ9.C869yRdrXIiZKkl_tS5YA-9ZM143w7_CRt0Ai35evcQG7wyTZ-3c_5xzf_iyU2CpDvgwZuZUIU41DMZY6H_nRA' \
      --header 'Action: OPEN_SHIFT' \
      --header 'Content-Type: application/json' \
      --data-raw '{
          "employee_name": "A.A. Hlypalo"
      }'
    
  * Закрытие смены:
    
      curl --location --request POST 'http://localhost:8080/shift' \
      --header 'Authorization: eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhaGx5cGFsbzEyM0BnbWFpbC5jb20iLCJpYXQiOjE2NDQyNTEwMTZ9.C869yRdrXIiZKkl_tS5YA-9ZM143w7_CRt0Ai35evcQG7wyTZ-3c_5xzf_iyU2CpDvgwZuZUIU41DMZY6H_nRA' \
      --header 'Action: CLOSE_SHIFT' \
      --header 'Content-Type: application/json' \
      --data-raw '{
          "employee_name": "A.A. Hlypalo"
      }'
      
  * Получение списка чеков:
    
      curl --location --request GET 'http://localhost:8080/check?orderColumn=TOTAL' \
      --header 'Authorization: eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhaGx5cGFsbzEyM0BnbWFpbC5jb20iLCJpYXQiOjE2NDQyNTEwMTZ9.C869yRdrXIiZKkl_tS5YA-9ZM143w7_CRt0Ai35evcQG7wyTZ-3c_5xzf_iyU2CpDvgwZuZUIU41DMZY6H_nRA'
    
  * Пример отвера: 
    
      [
          {
              "id": 1,
              "products": [
                  {
                      "id": 1,
                      "name": "Капучино",
                      "price": 120.00,
                      "barCode": "123456",
                      "amount": null,
                      "photoUrl": "http://localhost:8080/user-photos/123-123-123.jpg",
                      "merchant": {
                          "id": 3,
                          "email": "ahlypalo123@gmail.com",
                          "details": null
                      }
                  }
              ],
              "date": 1644252401570,
              "total": 240.00,
              "shift": {
                  "id": 1,
                  "employeeName": "A.A. Hlypalo",
                  "startDate": 1644252302864,
                  "endDate": null,
                  "merchant": {
                      "id": 3,
                      "email": "ahlypalo123@gmail.com",
                      "details": null
                  }
              },
              "merchantDetails": null,
              "merchant": {
                  "id": 3,
                  "email": "ahlypalo123@gmail.com",
                  "details": null
              }
          }
      ]
    
 
