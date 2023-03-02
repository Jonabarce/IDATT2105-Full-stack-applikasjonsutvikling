Cypress.on('uncaught:exception', (err, runnable) => {
  return false;
});

describe('Contact Form', () => {

  it('shows success message on valid form submission', () => {
    cy.visit('/ContactPage') // replace with the URL of your contact page

    // Fill out the form with valid data
    cy.get('#firstname').type('John Doe')
    cy.get('#email').type('john.doe@example.com')
    cy.get('#message').type('This is a test message')

    // Intercept the form submission and return a successful response with a status code of 201, and alias the interception as "formSubmission"
    cy.intercept("POST", "http://localhost:3000/form", {
      statusCode: 201,
    }).as("formSubmission")

    // Submit the form
    cy.get('#submit').click()

    // Wait for the form submission to complete and verify that a successful response with a status code of 201 is returned
    cy.wait('@formSubmission').then((interception) => {
      expect(interception.response.statusCode).to.equal(201);
    });

    // Verify that the success message is displayed
    cy.get('#theSubmitText').should('have.value', '')

    // Verify that the form fields were reset
    cy.get('#firstname').should('have.value', 'John Doe')
    cy.get('#email').should('have.value', 'john.doe@example.com')
    cy.get('#message').should('have.value', '')
  })


  it('disables submit button when form is invalid', () => {
    cy.visit('/ContactPage')
    // Fill in form with invalid data
    cy.get('#firstname').type('John')
    cy.get('#email').type('invalidemail')
    cy.get('#message').type('Test message')

    // Submit button should be disabled
    cy.get('#submit').should('be.disabled')
  })


<<<<<<< HEAD
})





=======



})

  
>>>>>>> 2c07fe2cede6c7f814c211607dfb5923368d821d
