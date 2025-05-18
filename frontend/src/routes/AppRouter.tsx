import { BrowserRouter, Routes, Route } from "react-router-dom";
import HomePage from "../pages/Home/Home.page";
import CreateItineraryPage from "../pages/Create/Create-Itinerary.page";

const AppRouter = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/create" element={<CreateItineraryPage />} />
      </Routes>
    </BrowserRouter>
  );
}

export default AppRouter;
