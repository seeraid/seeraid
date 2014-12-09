package org.seeraid.forecastengine.models;


/**
 * Represents the case when there is insufficient data available to return a
 * valid forecast value.
 * @since 0.4
 */
public class InsufficientDataException extends Exception
{
    private static final long serialVersionUID = 500L;
    
    /**
     * Default constructor. If possible, use the one argument constructor and
     * give more information about the lack of data.
     */
    public InsufficientDataException()
    {
        super();
    }
    
    /**
     * Constructs a new InsufficientDataException with the given reason.
     * This is the preferred constructor.
     * @param reason the reason for, or details about, the
     * InsufficientDataException.
     */
    public InsufficientDataException( String reason )
    {
        super( reason );
    }
}
