# Adoption of React Navigation

## Context and problem statement

The app aims to implement a vertical-sliced, feature-first architecture, where each module owns its responsibilities - screens, API clients, hooks, utilities, and components. The app also requires routing between views, including on-demand navigation triggered by user actions, such as clicking a "Sign In" button to navigate to the Home screen.

Currently, we are using Expo Router, which enforces file-based routing under the `app/` directory. This creates several concrete issues:

- Feature-first architecture is blocked: non-component files in app/ are treated as routes, preventing colocating logic, hooks, or utils inside features.

- Split navigation responsibilities: features must know route paths (PostList.tsx → app/posts/[id].tsx) while app/ provides route shells, confusing ownership.

## Options considered

### Option 1: Put everything in `app/` and ignore vertical slicing

All modules live directly under `app/`. Features are tightly coupled to the routing system, and routing is entirely file-based.
Screens, hooks, utilities, and API clients are either kept in component files or externalized to other directories.

Pros:

- No setup overhead- features can be implemented immediately

Cons:

- Big ball of mud: features, screens, API clients, and utilities all live together
- Tightly coupled to routing: screens cannot exist independently of the file-based routing system
- No modularity: hard to organize code by responsibility
- Scalability issues: adding more screens or features increases complexity
- No locality of behaviour: logic is distributed across the app (screens/hooks/api/config)

<details open>
  <summary>Example </summary>
  <br>
  
```
app/
├── app.tsx
├── screens/
│   ├── home-screen.tsx
│   ├── login-screen.tsx
│   ├── profile-screen.tsx
│   └── settings-screen.tsx
├── auth/
│   ├── login-form.tsx
│   └── register-form.tsx
├── profile/
│   ├── profile-card.tsx
│   └── edit-profile-form.tsx
├── posts/
│   ├── post-card.tsx
│   └── post-list.tsx
├── notifications/
│   ├── notification-list.tsx

hooks/
├── use-authentication.ts
├── use-fetch-user.ts
├── use-fetch-posts.ts
├── use-form-validation.ts
├── use-network-status.ts
├── use-notifications.ts
└── use-profile-updates.ts

api/
├── api-client.ts
├── auth-api.ts
├── profile-api.ts
├── posts-api.ts
└── notifications-api.ts

config/
├── constants.ts
├── utils.ts
└── global-styles.ts
  
```

</details>

### Option 2: Outsource implementation to `features/`, keep `app/` as routing shell

Screens, hooks, utilities, and API clients live in `features/`. The `app/` folder serves as a thin shell for Expo Router- re-exporting feature screens to create routes.

Pros:

 - Features are mostly modular - implementation is organized in `features/`.
 - Keeps the vertical-slice concept alive.

Cons:

 - `app/` becomes mostly boilerplate. For every screen, we need a file like:

      ```ts
      // app/auth/login.tsx
      export { default } from '@/features/auth/LoginScreen';
      ```
 - Routing responsibilities are split:

     - On-demand navigation in features (e.g., clicking "Sign In" → `router.push('/home')`)
     - Routing via `app/` routes (Expo Router automatically mapping files to paths)

    
       Both sides now "know" about navigation, creating ambiguity and duplicated effort
   
 - Tedium: Importing screens into `app/` just to re-export is boring, repetitive, and error-prone

Still constrained by Expo Router - we cannot freely place all implementation inside a source directory nor in `/app`
Any feature code must exist outside the `app/` folder, creating unnecessary separation

### Option 3: Drop Expo Router and adopt React Navigation

Switch to React Navigation. All features, screens, components, hooks, and utilities live inside `src/`
Routing is fully explicit and controlled via navigators (stack, tab, modal). The `app/` folder can be removed entirely

Pros:

 - Features own their screens and navigation: Buttons, hooks, and other interactions handle on-demand navigation entirely within the feature
 - Eliminates boilerplate: No more repetitive import/export in `app/`
 - Fine-grained control: Dynamic navigation (like "Sign In → Home") is entirely under feature responsibility
 - Full vertical slice support: Screens, API clients, hooks, and components live together in `features/`
 - Learning opportunity: Developers gain experience with React Navigation patterns and feature-first design

Cons:

 - Requires upfront setup - minor delay in building features
 - Extra time investment might not strictly be necessary for a small app


## Decision

React Navigation will be adopted

 - Features will live in `src/features/` with screens, hooks, API clients, and utilities
 - Components and shared code live in `src/components/` or `src/lib/`
 - Features handle all on-demand navigation, removing ambiguity and unnecessary boilerplate

## Rationale

 - Preserves vertical slice architecture fully
 - Routing responsibilities are clear: features manage their own navigation
 - Reduces boilerplate and tedium of re-exporting in `app/`
 - Avoids "split navigation" problem seen with Expo Router (button clicks inside features don’t require `app/` to exist)
 - Small delay in feature development is justified by long-term maintainability and modularity
 - Supports learning for developers and a clean codebase


## Anticipated Outcomes

 - Developer happiness: less boilerplate, clear responsibilities, app itself is easier to understand
 - Maintainability: modular and vertically sliced architecture
 - Scalability: future screens, stacks, or tabs can be added without constraints
 - Dynamic navigation is clean: features own routing for button clicks, form submissions, and conditional flows
 - Minor delay in feature development due to setup - acceptable trade-off

## Conclusion

React Navigation resolves the split responsibility problem and eliminates the unnecessary `app/` boilerplate imposed by Expo Router. Features can handle on-demand navigation like button clicks naturally, screens live entirely within `features/`, and the project becomes maintainable, modular, and scalable.

> Even if the app is small, this choice is a future-proof investment in architecture and developer learning, avoiding technical debt and providing a clean base for growth.
