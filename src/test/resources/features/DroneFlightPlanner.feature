# Acceptance Criteria
# 1. Navigate to the "Create Flight Plan" option.
# 2. Verify the user landed on the correct page
# 3. Verify the Plus Icon clickable
# 2. Click on the map viewer to set the initial point.
# 3. Click on the map to add a point.
# 4. Verify the point added and Flight Plan created
# 5. Click on additional locations on the map to add more points.
# 6. Verify the multiple points added
# 7. Name the flight plan and confirm.
# 8. Add another flight plan and verify multiple flight plans added
# 9. Switching between flight plans & Reloading the page
# by QA MSEN

Feature: Verify the map viewer loads correctly.

  Background: User lands on the Main Page
    Given the user lands on the Main Page

  Scenario: TC_01 Verify the map viewer loads correctly.
    Then the user verifies that the url of the page is "https://stupendous-birth.surge.sh/"
    Then the user verifies that the titles are visible on the Main Page
      | Drone Flight Planner       |
      | Mission                    |
      | This is                    |
      | Mission                    |
      | , our drone flight planner |
      | Flight Plans               |
      | No Flight Plan             |
      | Create the                 |
      | first one                  |
      | Create a new Flight Plan   |

  Scenario: TC_02 User clicks a point on the map
    Then the user verifies that the Plus Icon is clickable
    And the user clicks on the Plus Icon to set the initial point
    Then the user verifies that the text Choose a point near your take off position to start creating your flight plan. is visible
    And the user clicks a point on the map
    Then the user verifies that the text Good! your Flight Plan has been created. You can now add more checkpoints. is visible
    Then the user verifies that the Flight Plan Coordinates appears under the Flight Plans title
    Then the user verifies that created first flight point number appears on the flight plan

  Scenario: TC_03 User verifies that the text Your data are saved along the way, no need to worry...
    And the user clicks on the Plus Icon to set the initial point
    And the user clicks to another point on the map
    Then the user verifies that the text Your data are saved along the way, no need to worry... is visible
    Then the user verifies that created second flight point number appears on the flight plan

  Scenario: TC_04 User clicks multiple points on the map
    And the user clicks on the Plus Icon to set the initial point
    And the user clicks multiple points on the map
    And the user inputs a Flight Description
    Then the user verifies that the Flight Description is displayed
    Then the user verifies that the coordinates match the points on the map

  Scenario: TC_05 User adds multiple Flight Plans
    And the user clicks on the Plus Icon to set the initial point
    And the user clicks multiple points on the map
    And the user inputs a Flight Description
    Then the user verifies that the Flight Description is displayed
    And the user clicks on the created Flight Plan
    And the user clicks on the Plus Icon to set the initial point
    And the user adds another flight Plan
    And the user inputs second Flight Description
    Then the user verifies that the Flight Description is displayed
    And the user clicks on the created second Flight Plan
    Then the user verifies that there are multiple Created Flight Plan displayed

  Scenario: TC_06 User Switches between flight plans & Reload the page
    And the user clicks on the Plus Icon to set the initial point
    And the user clicks multiple points on the map
    And the user inputs a Flight Description
    Then the user verifies that the Flight Description is displayed
    And the user clicks on the created Flight Plan
    And the user clicks on the Plus Icon to set the initial point
    And the user adds another flight Plan
    And the user inputs second Flight Description
    Then the user verifies that the Flight Description is displayed
    And the user clicks on the created second Flight Plan
    Then the user verifies that there are multiple Created Flight Plan displayed
    And the user switches between flight plans
    And the user reloads the page
    Then the user verifies that there are multiple Created Flight Plan displayed
