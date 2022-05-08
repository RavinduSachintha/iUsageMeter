# iUsageMeter

## SLT

#### Authentication Request

```shell
curl --request POST \
  --url https://omniscapp.slt.lk/mobitelint/slt/sltvasoauth/oauth2/token \
  --header 'Accept: application/json' \
  --header 'Content-Type: application/x-www-form-urlencoded' \
  --data client_id=622cc49f-6067-415e-82cd-04b1b76f6377 \
  --data grant_type=password \
  --data scope=scope1 \
  --data username=<Your Account Username> \
  --data password=<Your Account Password>
  ```

#### Get Usage (VAS_DATA)
```shell
curl --request GET \
  --url https://omniscapp.slt.lk/mobitelint/slt/sltvasservices/dashboard/vas_data \
  --header 'Authorization: Bearer <Access Token from Authentication Response>' \
  --header 'Content-Type: application/json' \
  --header 'subscriberid: <Metadata from Authentication Response>' \
  --header 'x-ibm-client-id: 622cc49f-6067-415e-82cd-04b1b76f6377'
```