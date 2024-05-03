describe('Login', () => {

  it('can log in', () => {
    cy.visit('http://localhost')

    cy.get('[name="signup"] > [data-cy="email"]').type('login@somewhere.org')
    cy.get('[name="signup"]').submit()

    cy.get('[data-cy="confirmationLink"]').click()

    cy.get('[name="login"] > [data-cy="email"]').type('login@somewhere.org')
    cy.get('[name="login"]').submit()

    cy.get('[data-cy="magicLink"]').click()

    cy.contains('Secured Content')
  })

  it('fails to log in', () => {
    cy.visit('http://localhost')

    cy.get('[name="login"] > [data-cy="email"]').type('does-not-exist@somewhere.org')
    cy.get('[name="login"]').submit()

    cy.get('[data-cy="magicLink"]').should('not.exist')
    cy.get('[data-cy="refusalMessage"]').should('exist')
  })
  
})
