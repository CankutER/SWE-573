import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  id: "",
  name: "",
  about: "",
  public: true,
  templates: [],
  posts: [],
  subscriptions: [],
};

const communitySlice = createSlice({
  name: "community",
  initialState,
  reducers: {
    setCommunity: (state, action) => {
      state = action.payload;
    },
  },
});

export const { setCommunity } = communitySlice.actions;

export default communitySlice.reducer;
