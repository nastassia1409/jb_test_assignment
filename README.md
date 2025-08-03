### Test cases:
src/test/resources/test-cases/ 

### API tests:
src/test/java/tests

To launch tests please add API tokens in BaseTest class:
API_KEY_COMPANY_ADMIN
API_KEY_TEAM_ADMIN
API_KEY_VIEWER

### Found issues:
1. /customer/licenses/assign returns 200 when trying to assign license that belongs to another team.
I think there should be 4xx error.
2. /customer/changeLicensesTeam returns 200 with invalid (non-existing) license, 400 error would be more correct here.
