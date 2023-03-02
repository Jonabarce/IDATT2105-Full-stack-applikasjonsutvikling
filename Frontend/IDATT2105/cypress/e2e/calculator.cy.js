Cypress.on('uncaught:exception', (err, runnable) => {
  return false;
});

describe('calculator', () => {
  beforeEach(()=>{
  cy.visit('/CalculatorPage')
  })
  
  it('Finds objects', () => {
  cy.get('.input').find('#currentNumber').should('have.text', '0')
  cy.get('#button5').should('have.text', '5')
  })
  
  it('adds two numbers',() =>{
  cy.get('#button2').click();
  cy.get('#currentNumber').should('have.text', '2')
  cy.get('#buttonPlus').click();
  cy.get('#button2').click();
  cy.get('#buttonSolve').click();
  cy.get('#currentNumber').should('have.text', '4')
  })
  
  it('subtracts two numbers',() =>{
  cy.get('#button5').click();
  cy.get('#buttonSub').click();
  cy.get('#button3').click();
  cy.get('#buttonSolve').click();
  cy.get('#currentNumber').should('have.text', '2');
  })
  
  it('should not get wrong output',() =>{
  cy.get('#button8').click();
  cy.get('#buttonMul').click();
  cy.get('#button7').click();
  cy.get('#buttonSolve').click();
  cy.get('#currentNumber').should('not.have.text', '60');
  })
  
  })
  
  