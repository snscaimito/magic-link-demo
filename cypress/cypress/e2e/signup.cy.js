describe('Signup', () => {

  it('signs up and confirms', () => {
    cy.visit('http://localhost')

    cy.get('[name="signup"] > [data-cy="email"]').type('someone@somewhere.org')
    cy.get('[name="signup"]').submit()

    cy.get('[data-cy="confirmationLink"]').click()

    cy.contains('Your confirmation was accepted.')
  })
  
})