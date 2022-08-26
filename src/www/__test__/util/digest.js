export function createDigestFunction(typeName, digestElement) {
  const digestFunction = (element) => {
    const digest = {
      typeName,
      // why getElement and not element? Because it is more easy to show
      getElement: () => element,
      ...digestElement(element),
    };
    return digest;
  };

  Object.defineProperty(digestFunction, "name", {
    value: `digest${typeName[0].toUpperCase()}${typeName.slice(1)}`,
  });
  return digestFunction;
}
