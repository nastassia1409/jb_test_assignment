### Test cases:
src/test/resources/test-cases/ 

### API tests:
src/test/java/tests

To launch tests please add API tokens in BaseTest class: 
- API_KEY_COMPANY_ADMIN 
- API_KEY_TEAM_ADMIN 
- API_KEY_VIEWER

### Found issues:

1. /customer/changeLicensesTeam returns 200 with invalid (non-existing) license, 4xx error would be more correct here.
