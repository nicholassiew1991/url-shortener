import React from "react";
import ShortUrlForm from "components/shortUrlForm";
import ShortUrlTable from "components/shortUrlTable";
import Link from "./entities/link";

const App = () => {
  
  const [links, setLinks] = React.useState<Link[]>(JSON.parse(localStorage.getItem("links")) ?? []);

  React.useEffect(() => {
    localStorage.setItem("links", JSON.stringify(links));
  }, [links]);

  return (
    <>
      <ShortUrlForm links={links} setLinks={setLinks} />
      <ShortUrlTable links={links} />
    </>
  );
};

export default App;
