# User management

## Overview
This test suite covers user management for Admin and Viewers.

---

### TC01 – View and edit users list by company administrator
**Preconditions:**
- User is logged in with company administrator permission.

**Steps:**
- Go to the "Teams" screen.
- Go to some team screen.
- Go to the "Users" section.
- Check users and licenses.

**Expected result:**
- Company administrator has access to all teams inside the company.
- Company administrator can view team users list with licenses information.
- Company administrator can revoke licenses from user one by one and all together.
- Company administrator can transfer licenses.

---

### TC02 – View and edit users list by team administrator
**Preconditions:**
- User is logged in with team administrator permission.

**Steps:**
- Go to the team screen.
- Go to the "Users" section.
- Check users and licenses.

**Expected result:**
- Team administrators have access only to their group page.
- Team administrators can see users and their licenses on Users page.
- Team administrator can revoke licenses from user one by one and all together.
- Team administrators can not see "Transfer" licenses links in the users list.

---

### TC03 – View users list by viewer
**Preconditions:**
- User is logged in with viewer permissions.

**Steps:**
- Go to the team screen.
- Go to the "Users" section.
- Check users and licences.

**Expected result:**
- Viewers have access only to their group page.
- Viewers can see users and their licenses on Users page.
- Team administrators can not see "Revoke" and "Transfer" licences links in the users list.

---

### TC04 – Team administration by company administrator
**Preconditions:**
- User is logged in with company administrator permission.

**Steps:**
- Go to the "Teams" screen.
- Go to some team screen.
- Go to the "Administration" section.
- Check "Team Administration" section.

**Expected result:**
- Company administrator can see team administrators list.
- Company administrator can invite viewers, purchasers and admins.
- Company administrator can revoke all team members' API tokens.
- Company administrator can switch roles for team members from Admin to Viewer and back.
- Company administrator can remove access for all team members.

---

### TC05 – Team administration by team administrator
**Preconditions:**
- User is logged in with team administrator permission.

**Steps:**
- Go to the team screen.
- Go to the "Administration" section.
- Check "Team Administration" section.

**Expected result:**
- Team administrator can see their team administrators list.
- Team administrator can invite viewers, purchasers and admins.
- Team administrator can revoke all team members' API tokens.
- Team administrator can switch roles for team members from Admin to Viewer and back.
- Team administrator can remove access for all team members.
- Team administrator can leave team management.

---

### TC06 – Team administration by Viewer
**Preconditions:**
- User is logged in with team administrator permission.

**Steps:**
- Go to the team screen.
- Go to the "Administration" section.
- Check "Team Administration" section.

**Expected result:**
- Viewer can see team administrators list.
- Viewer can not invite team members.
- Viewer can revoke only their own API tokens.
- Viewer can not switch user roles (Admin/Viewer).
- Viewer can not remove access for team members.