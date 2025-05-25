import { BrowserRouter, Routes, Route } from "react-router-dom";
import HomePage from "../pages/Home.page";
import CreateItineraryPage from "../features/itinerary/pages/create-itinerary.page";
import ItineraryDetailPage from "../features/itinerary/pages/itinerary-detail.page";

const AppRouter = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/create" element={<CreateItineraryPage />} />
        <Route path="/itinerary/:id" element={<ItineraryDetailPage />} />
      </Routes>
    </BrowserRouter>
  );
}

export default AppRouter;
