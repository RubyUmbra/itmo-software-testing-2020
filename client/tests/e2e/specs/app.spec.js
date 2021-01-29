const PAGE_URL = "http://localhost:8081";

describe("Check home page", () => {
  it("Check home page", () => {
    cy.visit(PAGE_URL + "/");
    cy.get("h1").should("contain", "Home page");
  });
});

describe("Login test", () => {
  it("login user", () => {
    cy.visit(PAGE_URL + "/login");
    cy.get("a")
      .eq(5)
      .click();
    cy.get("#username").type("admin");
    cy.get("#password").type("password");
    cy.get("button").click();
    cy.get("header").should("contain", "Profile");
  });

  it("Incorrect login try", () => {
    cy.visit(PAGE_URL + "/login");
    cy.get("a")
      .eq(5)
      .click();
    cy.get("#username").type("admin1212w12");
    cy.get("#password").type("password12w2  q1");
    cy.get("button").click();
    cy.url().should("include", "login");
  });
});

describe("Check profile", () => {
  it("Check profile", () => {
    cy.visit(PAGE_URL + "/login");
    cy.get("a").eq(5).click();
    cy.get("#username").type("admin");
    cy.get("#password").type("password");
    cy.get("button").click();
    cy.url().should("include", "profile");
    cy.get("h3").should("contain", "admin");
    cy.get("p").eq(1).should("contain", "1");
    cy.get("p").eq(2).should("contain", "admin@local.com");
  });
});

describe("Check register page", () => {
  it("Check register page (incorrect register try)", () => {
    cy.get("a").eq(5).click();
    cy.visit(PAGE_URL + "/register");
    cy.get("#username").type("admin");
    cy.get("#email").type("admin@local.com");
    cy.get("#password").type("password");
    cy.get("button").click();
    cy.url().should("include", "register");
  });
});
