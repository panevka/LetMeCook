# Vertical-slice architecture and feature encapsulation

## Context and problem statement

The app aims to implement a vertical-slice, feature-first architecture, where each feature owns its implementation:

* Screens
* Hooks
* API clients
* Internal utilities

Each feature contains 2 directories:
- `internal/` - private module containing internal logic (hence the name), helpers, hooks, views. Cannot be imported by other features.
- `api/` - a thin directory exposing public API of the module, where re-exports of internal logic / creation of facades occurs. 

Without explicit boundaries, accidental cross-feature imports can occur, causing:

* Tight coupling between implementation details of features
* Leaky abstractions where private helpers become externally accessible
* Maintenance risks, as changes in one feature may break others that rely on its internals

```
src/
├── features/
│   ├── auth/
│   │   ├── api/
│   │   │   └── index.ts            # Exported: useLogin(), useLogout(), useRegister()
│   │   └── internal/
│   │       ├── screens/
│   │       │   ├── LoginScreen.tsx
│   │       │   ├── RegisterScreen.tsx
│   │       │   └── ProfileScreen.tsx
│   │       ├── hooks/
│   │       │   ├── useAuthentication.ts
│   │       │   ├── useProfile.ts
│   │       │   └── useFormValidation.ts
│   │       ├── utils/
│   │       │   ├── auth-validators.ts
│   │       │   ├── profile-utils.ts
│   │       │   └── token-manager.ts
│   │       └── services/
│   │           ├── auth-service.ts
│   │           └── profile-service.ts
│   │
│   └── dashboard/
│       ├── api/
│       │   └── index.ts            # Exported: useGetStats(), useGetUserSummary(), useGetNotifications()
│       └── internal/
│           ├── screens/
│           │   ├── DashboardScreen.tsx
│           │   ├── OverviewPanel.tsx
│           │   └── NotificationsPanel.tsx
│           ├── hooks/
│           │   ├── useStats.ts
│           │   ├── useUserSummary.ts
│           │   └── useNotifications.ts
│           ├── utils/
│           │   ├── dashboard-formatters.ts
│           │   └── notification-utils.ts
│           └── services/
│               ├── dashboard-service.ts
│               └── notification-service.ts
├── components/
│   ├── Button.tsx
│   ├── Card.tsx
│   └── Header.tsx
└── lib/
    ├── utils.ts
    └── constants.ts

```

## Options considered

### Option 1: Rely on developer discipline

All developers are trusted to respect feature boundaries, and code reviews enforce internal module usage.

Pros:

* No setup required, development starts immediately

Cons:

* Human error is common
* Violations may go unnoticed even after a code review
* Tight coupling can gradually undermine vertical-slice architecture


### Option 2: ESLint import rules

Use ESLint plugins such as `eslint-plugin-import` or `stricter-imports` to restrict cross-feature imports.

Pros:

* Lightweight, integrates with editors and CI
* Provides warnings or errors for static imports

Cons:

* Dynamic imports or `require()` bypass rules
* Cannot automatically allow same-feature internal imports
* Per-feature exceptions require explicitly defining patterns


### Option 3: TypeScript `declare module`

Use TypeScript to declare internal modules as private.

Pros:

* Compile-time enforcement

Cons:

* Blocks internal communication within the same feature (for example, `api` importing `internal`)

### Option 4: Dependency Cruiser

Use Dependency Cruiser to enforce boundaries while allowing internal communication within each feature.

Pros:

* Specifically dedicated for this
* Works for static and dynamic imports
* Supports complex rules and patterns
* CI integration ensures automatic enforcement
* Compatible with existing folder structure
* Supports thin public APIs and facades without exposing internals

Cons:

* Requires initial setup and learning
* Has to be added as an extra step to CI

Example rule:

```
{
  "name": "internal-only-accessible-within-feature",
  "comment": "Internal modules may only be imported within the same feature",
  "severity": "error",
  "from": { "pathNot": "^features/([^/]+)/(api|internal)/" },
  "to": { "path": "^features/([^/]+)/internal/", "pathNot": "^features/$1/(api|internal)/" }
}
```

Enforcement examples:

* Correct:

```
import { useAuthentication } from 'features/auth/api';
```

* Incorrect (throws errror):

```
import { useAuthentication } from 'features/auth/internal/use-authentication';
```

Imported in `features/posts` will fail CI.

## Decision

We will adopt vertical-slice, feature-first architecture with thin APIs and Dependency Cruiser enforcement:

* Features live in `src/features/` and own screens, hooks, API clients, and internal utilities
* Internal modules are private to the feature
* Public modules are exposed via `api/` or `index.ts` facades
* Shared components and utilities live in `src/components/` or `src/lib/`
* CI automatically fails on cross-feature internal imports

This preserves modularity, maintainability, and predictable dependencies while enforcing feature boundaries.

## Rationale

* Preserves vertical-slice architecture fully
* Features are self-contained and maintainable
* Prevents accidental coupling and leaky abstractions
* Thin APIs and facades allow internal refactoring without breaking consumers
* Handles static and dynamic imports reliably, unlike ESLint or TypeScript-only approaches
* Supports long-term maintainability, scalability, and developer clarity


## Anticipated Outcomes

* Clear feature boundaries and developer clarity
* Modular features that are easy to understand and refactor
* CI ensures accidental cross-feature imports are prevented
* Scalable architecture that allows safe addition of new features
* Consumers interact only with stable thin APIs

## Conclusion

Adopting vertical-slice, feature-first architecture with thin APIs and Dependency Cruiser enforcement ensures a clean, modular, maintainable, and scalable codebase. Each feature controls its own internals, shared code is centralized, and accidental cross-feature coupling is prevented. This provides a robust foundation for future growth and long-term maintainability.

> Thin contracts, internal facades, and automated enforcement together preserve modularity, encapsulation, and predictability.
