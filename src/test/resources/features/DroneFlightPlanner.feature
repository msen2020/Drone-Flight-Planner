# Test Steps:
# 1. Navigate to the "Create Flight Plan" option.
# 2. Verify the user landed on the correct page
# 2. Click on the map viewer to set the initial point.
# 3. Click on additional locations on the map to add more points.
# 4. Save the flight plan by clicking the "Save" button.
# 5. Name the flight plan and confirm.
# by QA MSEN

Feature: Verify the map viewer loads correctly.

  Background: User lands on the Main Page
    Given the user lands on the Main Page

  Scenario: TC_01 Create Flight Plan  Verify the map viewer loads correctly.
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

  Scenario: TC_02 Create Flight Plan
    Then the user verifies that the Plus Icon is clickable
    And the user clicks on the Plus Icon to set the initial point
    Then the user verifies that the text Choose a point near your take off position to start creating your flight plan. is visible
    And the user clicks a point on the map
    Then the user verifies that the text Good! your Flight Plan has been created. You can now add more checkpoints. is visible
    Then the user verifies that the Flight Plan Coordinates appears under the Flight Plans title
    And the user the Flight Description
    And the user clicks on the created Flight Plan

    Scenario: TC_03 User clicks multiple points on the map
      And the user clicks on the Plus Icon to set the initial point
      And the user clicks multiple points on the map
# 3. Click on additional locations on the map to add more points.
# 4. Save the flight plan by clicking the "Save" button.
# 5. Name the flight plan and confirm.