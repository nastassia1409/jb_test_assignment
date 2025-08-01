# Team Merge

## Overview
This test suite covers team merge feature.

---

### TC01 – Merge two teams as an organization admin
**Preconditions:**
- User is logged in with company administrator permission.
- Teams A and B exist.

**Steps:**
1. Go to the "Teams" screen.
2. Select Team A from team list.
3. On the team page go to "Administration" section.
4. Click "Merge" button.
5. Select Team B from the list.
6. Confirm clicking "Merge" button.

**Expected result:**
- A confirmation message is shown.
- Team A is no longer listed.
- Team B is still listed with updated licenses, users and admins.
- All bulk invitations created in Team A are transferred to Team B, including the expired ones
- The AI Assistant access settings for Team B stay unchanged after the merge.
---

### TC02 – Merge option is hidden for non-admin users
**Preconditions:**
- Log in as a user who is not a company admin.

**Steps:**
1. Navigate to the team management UI.
2. Select a team and go to "Administration" section.

**Expected result:**
- No "Merge" button visible.

---

### TC03 – Licenses from Team A show in Team B after merge
**Preconditions:**
- Team A and team B have active licenses.

**Steps:**
1. Merge Team A into Team B.
2. Open Team B’s license section.

**Expected result:**
- Total license count is the sum of Team A and Team B licences count.
- Licenses from Team A are visible under Team B.

---

### TC04 – Admins from Team A are added to Team B
**Preconditions:**
- Team A and team B each have several admins.
- There is at least one common admin in Team A and Team B.

**Steps:**
1. Merge Team A into Team B.
2. Open the list of admins for Team B.

**Expected result:**
- Admins from Team A appear in Team B’s admin list.
- No duplicates if a user was admin in both teams.

---

### TC05 – Bulk invites are transferred to Team B
**Preconditions:**
- Team A has some active and some expired bulk invites.

**Steps:**
1. Merge Team A into Team B.
2. Go to the invitations section of Team B.

**Expected result:**
- All invites from Team A are listed under Team B.
- Expired invites keep their expired status.

---

### TC06 – AI Assistant setting in Team B stays the same
**Preconditions:**
1. AI Assistant is enabled in Team B.
2. AI Assistant is disabled in Team A.

**Steps:**
1. Merge Team A into Team B.
2. Open the AI Assistant settings in Team B.

**Expected result:**
- Team B’s AI Assistant setting remains unchanged.
- Team A’s setting is ignored.

---

### TC07 – Team A is removed from the team list
**Steps:**
1. Merge Team A into Team B.
2. Go the team list and search for Team A.

**Expected result:**
- Team A is no longer visible in the UI.

---

### TC08 – Merging a team into itself is not allowed
**Steps:**
1. Open the merge dialog for Team A.
2. Try selecting Team A as the destination.

**Expected result:**
- Team is not available in the list of destination teams

---

### TC09 
