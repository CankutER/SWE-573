import { Link } from "react-router-dom";
import { useLogout } from "../utilities/auth";

export function Navbar() {
  const logout = useLogout();
  return (
    <nav className="nav">
      <ul className="nav nav-pills">
        <li className="nav-item">
          <Link to="/home" className="nav-link">
            Home
          </Link>
        </li>
        <li className="nav-item">
          <Link to="/community/create" className="nav-link">
            Create Community
          </Link>
        </li>
        <li className="nav-item">
          <Link to="/user" className="nav-link">
            Profile
          </Link>
        </li>
        <li onClick={logout} className="nav-item">
          <Link to="/" className="nav-link">
            Logout
          </Link>
        </li>
      </ul>
    </nav>
  );
}
