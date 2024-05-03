describe('Login', () => {

  it('can log in', () => {
    cy.visit('http://localhost')

    cy.get('[name="login"] > [data-cy="email"]').type('someone@somewhere.org')
    cy.get('[name="login"]').submit()

    cy.get('[data-cy="magicLink"]').click()

    cy.contains('Secured Content')
  })

  it.only('fails to log in', () => {
    cy.visit('http://localhost')

    cy.get('[name="login"] > [data-cy="email"]').type('does-not-exist@somewhere.org')
    cy.get('[name="login"]').submit()

    cy.get('[data-cy="magicLink"]').should('not.exist')
    cy.get('[data-cy="refusalMessage"]').should('exist')
  })
  
})
