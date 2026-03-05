import { JSX } from "react";

type RouteParams = Record<string, any>;

export type RouteDefinition<TPath extends string> = {
  readonly path: TPath;
  readonly component: () => JSX.Element;
  readonly params?: RouteParams;
};

export const makeRoute = <TPath extends string>(
  route: RouteDefinition<TPath>
) => route;
