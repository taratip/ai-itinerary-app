import { BrowserRouter, Routes, Route } from "react-router-dom";
import HomePage from "../pages/Home/Home.page";
// Add more imports as needed

export default function AppRouter() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<HomePage />} />
        {/* Other routes */}
      </Routes>
    </BrowserRouter>
  );
}
