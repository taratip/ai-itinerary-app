import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { itineraryService } from "../api/itinerary.service";
import "./Create-Itinerary.style.scss"

const CreateItineraryPage = () => {
  const navigate = useNavigate();

  const [title, setTitle] = useState("");
  const [destination, setDestination] = useState("");
  const [startDate, setStartDate] = useState("");
  const [endDate, setEndDate] = useState("");
  const [budget, setBudget] = useState("");
  const [interests, setInterests] = useState<string[]>([]);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);


  const allInterests = ["Food", "Culture", "Nature", "Adventure", "Relaxation"];

  const handleTitleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setTitle(e.target.value);
  }

  const handleDestinationChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setDestination(e.target.value);
  }

  const handleStartDateChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setStartDate(e.target.value);
  }

  const handleEndDateChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setEndDate(e.target.value);
  }

  const handleBudgetChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setBudget(e.target.value);
  }

  const toggleInterest = (interest: string) => {
    setInterests((prev) =>
      prev.includes(interest) ? prev.filter((i) => i !== interest) : [...prev, interest]
    );
  }

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    // TODO: Send to backend for AI generation
    console.log({ destination, startDate, endDate, budget, interests });
    setIsLoading(true);
    setError(null);

    try {
      const response = await itineraryService.createItinerary({
        title,
        destination,
        startDate,
        endDate,
        notes: `Budget: $${budget}`,
        travelers: [],
        dayPlans: []
      });

      navigate(`/itinerary/${response.id}`, { state: response });
    } catch (err) {
      setError(err instanceof Error ? err.message : "Failed to create itinerary");
      console.error("Error creating itinerary:", err);
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="create-itinerary-container">
      <h2 className="create-itinerary-title">Create Your Trip</h2>
      <form onSubmit={handleSubmit} className="create-itinerary-form">
        <div>
          <label className="form-label">Title</label>
          <input
            type="text"
            value={title}
            onChange={handleTitleChange}
            className="form-input"
            required
          />
        </div>

        <div>
          <label className="form-label">Destination</label>
          <input
            type="text"
            value={destination}
            onChange={handleDestinationChange}
            className="form-input"
            required
          />
        </div>

        <div className="form-group">
          <div className="form-group-item">
            <label className="form-label">Start Date</label>
            <input
              type="date"
              value={startDate}
              onChange={handleStartDateChange}
              className="form-input"
              required
            />
          </div>
          <div className="form-group-item">
            <label className="form-label">End Date</label>
            <input
              type="date"
              value={endDate}
              onChange={handleEndDateChange}
              className="form-input"
              required
            />
          </div>
        </div>

        <div>
          <label className="form-label">Budget ($)</label>
          <input
            type="number"
            value={budget}
            onChange={handleBudgetChange}
            className="form-input"
            required
          />
        </div>

        <div>
          <label className="create-itinerary-interests">Interests</label>
          <div className="create-itinerary-interests-container">
            {allInterests.map((interest) => (
              <button
                key={interest}
                type="button"
                className={`interest-button ${
                  interests.includes(interest)
                    ? "interest-button--selected"
                    : "interest-button--unselected"
                }`}
                onClick={() => toggleInterest(interest)}
              >
                {interest}
              </button>
            ))}
          </div>
        </div>

        <button
          type="submit"
          className="submit-button"
          disabled={isLoading}
        > 
          {isLoading ? 'Creating...' : '🪄 Generate Itinerary'}
        </button>

        {error && (
          <div className="error-message">
            {error}
          </div>
        )}
      </form>
    </div>
  );
}

export default CreateItineraryPage;
