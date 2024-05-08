import { Outlet, useLocation, useNavigate } from "react-router-dom";
import { Link } from "react-router-dom";
import { Navbar } from "./nav";
import { AuthService } from "../utilities/auth";
import { useEffect, useState } from "react";
export function SharedLayout() {
  const location = useLocation();
  const navigate = useNavigate();
  const [isAllowed, setIsAllowed] = useState(false);
  const checkLogin = async () => {
    return await AuthService.isloggedIn();
  };
  useEffect(() => {
    async function protectRoute() {
      const isloggedIn = await checkLogin();
      if (!isloggedIn) {
        location.pathname !== "/" ? navigate("/") : setIsAllowed(true);
      } else {
        location.pathname === "/" ? navigate("/user") : setIsAllowed(true);
      }
      return () => {
        setIsAllowed(false);
      };
    }

    protectRoute();
  }, [navigate, location.pathname]);
  return (
    <div className="d-flex flex-column vh-100">
      <header className="header sticky-top bg-light shadow-sm">
        <div className="container d-flex justify-content-between align-items-center">
          <Link to="/" className="text-dark text-decoration-none fs-4">
            Communitter
          </Link>
          {location.pathname !== "/" && isAllowed && <Navbar></Navbar>}
        </div>
      </header>
      <main className="container flex-grow-1">
        {isAllowed && <Outlet></Outlet>}
      </main>
    </div>
  );
}
